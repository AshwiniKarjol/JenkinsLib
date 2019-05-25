def call(String sonarenv) {
withSonarQubeEnv("${sonarenv}") { 

      timeout(time: 1, unit: 'HOURS') { 

           def qg = waitForQualityGate() 

           if (qg.status != 'OK') {

             error "Pipeline aborted due to quality gate failure: ${qg.status}"

           }

        }
        
        }
        }
