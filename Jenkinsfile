pipeline {
    agent any
    stages {
        stage('Build Image'){
            steps {
                sh "packer build Packerfile.json"
            }
        }        
        
    }
}
