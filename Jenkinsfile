pipeline {
    agent any
    stages {
        stage('Build Image'){
            steps {
                sh "packer build Packerfile.json"
            }
        }
        stage('Create Version File'){
            steps {
                writeFile file: 'image.json', text: '{"description":"", "name":"" , "versions":""}'
            }
        }
        stage('Archive Image'){
            steps {
                archiveArtifacts artifacts: 'output-vagrant/*.box', onlyIfSuccessful: true                
            }
        }
        stage('Clean Workspace') {
            steps {
                cleanWs()   
            }
        }    
            
    }
   
    
    
}
