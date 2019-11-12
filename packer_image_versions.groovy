import groovy.json.JsonSlurper
import groovy.json.JsonOutput

   
  
def updateVersionStruct(String filename, String checksum, String build_number, String jobname, String jenkins_home){

    def buildNumber = Jenkins.instance.getItem(jobname).lastSuccessfulBuild.number
    def source = jenkins_home + "/jobs/" + jobname + "/builds/" + buildNumber + "/archive/" + filename 
    def target = filename 

    println(source)

    def jsonSlurper = new JsonSlurper()
    def data = jsonSlurper.parse(new File(source))

    def new_version = [:]

    new_version["providers"]=[]
    new_version["providers"].add(["checksum":checksum,"checksum_type":"sha256","name":"virtualbox","url":"http://admin:119de05359ae662811d8c7963663f22bbe@vps249917.ovh.net:8080/job/FirstPipeline/lastSuccessfulBuild/artifact/output-vagrant/package.box"])

    new_version["version"] = build_number 

    data.versions.push(new_version)

    def json_str = JsonOutput.toJson(data)

    
    println("String is ...")
    println(json_str)

    def json_beauty = JsonOutput.prettyPrint(json_str)

    File file = new File(jenkins_home + "/workspace/" + jobname + "/" + target)
    //file = new FilePath(new File(build.workspace.toString() + "/" + target))
    file.write(json_beauty)

    println(json_beauty)

} 


return this 
