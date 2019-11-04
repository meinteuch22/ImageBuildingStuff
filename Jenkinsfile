pipeline {
    agent any
    stages {
        stage('Build Image'){

            environment {
                GIT_COMMIT_HASH = sh (script: "git log -n 1 --pretty=format:'%H'", returnStdout: true)
            }


            steps {
                sh "packer build -var 'jenkins_build_nr=$BUILD_NUMBER' -var 'git_commit_hash=$GIT_COMMIT_HASH' Packerfile.json"
            }
        }
        stage('Load Image Version Config'){
            steps {        
                script{
                    code = load 'packer_image_versions.groovy'
                }
            }

        }
        stage('Create Version File'){

            environment {
                GIT_TAG = sh(returnStdout: true, script: "git tag --sort version:refname | tail -1").trim()
                CHECKSUM = sh(script: "awk '{print \$1}' output-vagrant/checksum.txt", returnStdout: true).trim()
            }
            steps {
                script{
                    updateVersionStruct('output-vagrant/image.json',"$CHECKSUM","$BUILD_NUMBER")
                }
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
