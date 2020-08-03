pipeline {
  agent {
		label 'GUISlave'
	}
   stages {
		stage('maven install') {
			steps {
				script {
					sh '''
						mvn clean install
					'''
				}
			}
		}
   }
   post {
        always {
			script {	   
				// publish html
				publishHTML target: [
					allowMissing: false,
					alwaysLinkToLastBuild: false,
					keepAll: true,
					reportDir: 'Reports',
					reportFiles: 'Consumption.html,FlexCoins.html,Loyalty.html,Management.html',
					reportName: 'New API Reports'
					]
			}
            junit 'target/*/*.xml'
        }
    }
}
