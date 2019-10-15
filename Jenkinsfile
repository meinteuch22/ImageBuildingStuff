pipeline {
    agent any
    stages {
        stage('Build Image'){
            steps {
                sh "packer build Packerfile.json"
            }
        }
        stage('Image Archivation'){
            steps {
                archiveArtifacts artifacts: 'output-vagrant/*.box', onlyIfSuccessful: true                
            }
        }
        
        
    }
    post { 
        always { 
            cleanWs()
        }
    }
    
    
}
