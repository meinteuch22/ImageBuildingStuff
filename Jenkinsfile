pipeline {
    agent any
    stages {
        stage('Build Image'){
            steps {
                sh "packer build Packerfile.json"
            }
        }
        stage('Create Version File'){
            environment {
                GIT_COMMIT_HASH = sh (script: "git log -n 1 --pretty=format:'%H'", returnStdout: true)
                GIT_TAG = sh(returnStdout: true, script: "git tag --sort version:refname | tail -1").trim()
                CHECKSUM = sh(script: "awk '{print \$1}' output-vagrant/checksum.txt", returnStdout: true).trim()
            }
            steps {
                sh "echo ${GIT_COMMIT_HASH}"
                writeFile file: 'output-vagrant/image.json', text: """{
                    "description":"StratoPro Ordering Vagrant Box",
                    "name":"StratoPro/Centos7" ,
                    "versions":[
                        {
                         "providers":[
                             {
                              "checksum":"$CHECKSUM",
                              "checksum_type":"sha256",
                              "name": "virtualbox",
                              "url":"http://admin:119de05359ae662811d8c7963663f22bbe@vps249917.ovh.net:8080/job/FirstPipeline/lastSuccessfulBuild/artifact/output-vagrant/package.box"    
                             }
                         
                         ],
                         "version": "$BUILD_NUMBER"    
                        }
                    
                    ]
            
                }"""
            }
        }
        stage('Archive Image'){
            steps {
                archiveArtifacts artifacts: 'output-vagrant/*', onlyIfSuccessful: true                
            }
        }
        stage('Clean Workspace') {
            steps {
                cleanWs()   
            }
        }    
            
    }
   
    
    
}
