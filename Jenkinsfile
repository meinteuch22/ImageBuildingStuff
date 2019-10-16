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
            }
            steps {
                sh "echo ${GIT_COMMIT_HASH}"
                writeFile file: 'output-vagrant/image.json', text: """{
                    "description":"StratoPro Ordering Vagrant Box",
                    "name":"StratoPro/centos7" ,
                    "versions":[
                        {
                         "providers":[
                             {
                              "checksum":"",
                              "checksum_type":"sha256",
                              "name": "virtualbox",
                              "url":"http://vps249917.ovh.net:8080/job/FirstPipeline/lastSuccessfulBuild/artifact/output-vagrant/package.box"    
                             }
                         
                         ],
                         "version": "$GIT_COMMIT_HASH"    
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
