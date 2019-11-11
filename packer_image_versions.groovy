import groovy.json.JsonSlurper
import groovy.json.JsonOutput
   
  
def updateVersionStruct(String filename, String checksum, String build_number, String jobname){

    def buildNumber = Jenkins.instance.getItem(jobname).lastSuccessfulBuild.number

    def jsonSlurper = new JsonSlurper()
    def data = jsonSlurper.parse(new File(filename))

    def new_version = [:]

    new_version["providers"]=[]
    new_version["providers"].add(["checksum":"$CHECKSUM","checksum_type":"sha256","name":"virtualbox","url":"http://admin:119de05359ae662811d8c7963663f22bbe@vps249917.ovh.net:8080/job/FirstPipeline/lastSuccessfulBuild/artifact/output-vagrant/package.box"])

    new_version["version"] = "$BUILD_NUMBER" 

    data.versions.push(new_version)

    def json_str = JsonOutput.toJson(data)
    def json_beauty = JsonOutput.prettyPrint(json_str)

    File file = new File(filename)
    file.write(json_beauty)

    println(json_beauty)

} 


return this 
