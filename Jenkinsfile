pipeline {

  environment {
    dockerimagename = "josefranciscosanchez/backend"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/JoseFranciscoSanchezGutierrez/devSecOpsBackend.git'
      }
    }
    stage('Build App') {
          steps{
            sh "mvn package -Dmaven.test.skip -Dsnyk.skip"
          }
    }

    stage('Scan') {
        steps {
            snykSecurity organisation: 'josefranciscosanchezgutierrez', projectName: 'devSecOpsBackend', severity: 'low', snykInstallation: 'Snyk', snykTokenId: 'snyk', targetFile: 'pom.xml'
        }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhublogin'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }

    stage('Deploying App to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "backend-deployment.yml", kubeconfigId: "kubernetes")
        }
      }
    }
  }
}