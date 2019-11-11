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
        stage('Create Version File'){

            environment {
                GIT_TAG = sh(returnStdout: true, script: "git tag --sort version:refname | tail -1").trim()
                CHECKSUM = sh(script: "awk '{print \$1}' output-vagrant/checksum.txt", returnStdout: true).trim()
            }
            steps {
                script{
                    code = load 'packer_image_versions.groovy'
                    code.updateVersionStruct('output-vagrant/image.json',"$CHECKSUM","$BUILD_NUMBER", "$JOB_NAME", "$JENKINS_HOME")
                }
            }
        }
        stage('Archive Image'){
            steps {
                archiveArtifacts artifacts: 'output-vagrant/*', onlyIfSuccessful: true                
            }
        }
            
    }
    post {
        always {
                cleanWs()   
        }
    }

    
}
