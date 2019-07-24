pipeline {
  agent {
    docker {
      image 'pgtoopx/bch-maven-chrome'
      args '--add-host="portalcomercial.qa.labchile.cl:200.14.169.120"'
    }
  }
  stages {
    stage('No impacto') {
      steps {
        sh 'mvn clean install -Dheadless=true'
      }
    }
  }
  post {
    always {
      archiveArtifacts(artifacts: 'target/', fingerprint: true)
      junit 'target/cucumber.xml'
      publishTestResults(serverAddress: 'http://jira.bch.bancodechile.cl:8080', projectKey: 'VTAPYME', filePath: 'target/cucumber-report/cucumber.json', format: 'Cucumber', autoCreateTestCases: false)
      cucumber(fileIncludePattern: 'target/cucumber-report/cucumber.json', sortingMethod: 'ALPHABETICAL')
    }

  }
}