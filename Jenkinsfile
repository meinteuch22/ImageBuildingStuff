pipeline {
    agent any
    stages {
        stage('Build Image'){
            steps {
                sh "packer build Packerfile.json"
            }
        }        
        
    }
    post {
        success {
             echo 'Image and dir should be removed from Workspace'   
        }    
    }
}
