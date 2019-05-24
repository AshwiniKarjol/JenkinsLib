@Library('my_shared_library')_
node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/AshwiniKarjol/HelloWorld.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'Maven'
   }
//   stage('Build') {
//       // Run the maven build
//       withEnv(["MVN_HOME=$mvnHome"]) {
//          if (isUnix()) {
//           // sh '"$MVN_HOME/bin/mvn" clean install sonar:sonar    -Dsonar.projectKey=AshwiniKarjol_HelloWorld    -Dsonar.organization=ashwinikarjol-github    -Dsonar.host.url=https://sonarcloud.io    -Dsonar.login=9ddb77a2794c1e89f68fb503712266fdcaf38b1a'
//           sh '"$MVN_HOME/bin/mvn" clean install sonar:sonar -Dsonar.host.url=http://104.211.213.175:9000 -Dsonar.login=07062dc8d19fea91d32cf9ca1d0dfdeb4633c747'
//          } else {
//             bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
//          }
//       }
//   }

stage('Build'){
   withSonarQubeEnv('SonarQube1') {
       sh "'${mvnHome}/bin/mvn' clean install sonar:sonar -Dsonar.host.url=http://104.211.213.175:9000 -Dsonar.login=07062dc8d19fea91d32cf9ca1d0dfdeb4633c747"
   } 
}
   
   stage('Sonar Quality gate')
   {   withSonarQubeEnv('SonarQube1') { 
      timeout(time: 1, unit: 'HOURS') { 
           def qg = waitForQualityGate() 
           if (qg.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${qg.status}"
           }
        }
   }
   
   }
   stage('Docker image build')
   {
       sh "docker build -t ash/image:${BUILD_NUMBER} ."
   }
   
   stage('Deploy artifacts')
   {
    artifactorylib "Artifactory1","./target/*.jar","libs-snapshot-repo"
   }
   
}
