node {
   def mvnHome
   def server =Artifactory.server 'art_01'
    try{
        stage('Preparation') {
        emailext body: "started", subject: 'job started', to: 'Durgamadhab.Dash@Mindtree.com'
        //git 'https://github.com/Durga2Dash/girish.git '
        git 'https://github.com/Durga2Dash/mvcDemo.git'
        //git 'https://github.com/Durga2Dash/zSpringAssign.git'
               
      mvnHome = tool 'Maven'
      }
    
    stage('SonarQube analysis') {
          withSonarQubeEnv('sonarqube1') {
                sh '''mvn sonar:sonar \
  -Dsonar.projectKey=Durga2Dash_mvcDemo \
  -Dsonar.organization=durga2dash-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=ee5bb4186a645554f897d8ccf2c80f584c588c0e'''
          }
    }
          stage("Quality Gate"){
          /*timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  emailext body: "${qg.status}", subject: 'job failed', to: 'Durgamadhab.Dash@Mindtree.com'
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
                 
              }
          }*/
      }       

       stage('Build') {
       sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install"
       //jiraComment body: 'Build succeeded now', issueKey: 'EW-1'
       jiraComment body: 'build done', issueKey: 'EW-1'
   }
      stage('Artifactory upload') {
                def uploadSpec = """{
     "files": [
          {
              "pattern": "/var/lib/jenkins/workspace/sql_tomcat_container/target/*.war",
              "target": "repo-snapshot"
          }
        ]
        }"""
    server.upload(uploadSpec)
    // emailext body: "Upload successful", subject: 'job failed', to: 'Durgamadhab.Dash@Mindtree.com'
        }
        
    stage('downloading artifact')
        {
            def downloadSpec="""{
            "files":[
            {
                "pattern":"repo-snapshot/zSpringAssign.war",
                "target":"/var/lib/jenkins/warFiles/"
            }
            ]
            }"""
        server.download(downloadSpec)
        }
    stage ('Final deploy'){
        sh 'scp /var/lib/jenkins/warFiles/zSpringAssign.war minduser@tomcat1234.eastus.cloudapp.azure.com:/opt/tomcat/apache-tomcat-8.5.14/webapps/'
    }
   }
   catch(err)
   {
    //   emailext body: "${err}", subject: 'job failed', to: 'Durgamadhab.Dash@Mindtree.com'
       currentBuild.result = 'FAILURE'
   }
     stage('JIRA') {
        withEnv(['JIRA_SITE=jira1']) {
             if(currentBuild.result == 'FAILURE'){
                 if(currentBuild.previousBuild.result!='FAILURE'){
                    def testIssue = [fields: [ project: [key: 'EW'],
                                 summary: 'Build Fail',
                                 description: 'Build has failed.',
                                 issuetype: [name: 'Bug']]]

                    response = jiraNewIssue issue: testIssue
                    jiraAssignIssue idOrKey: response.data.key, userName: 'adityajain3896'

                    echo response.successful.toString()
                    echo response.data.toString()
                }
                else if(currentBuild.previousBuild.result=='FAILURE'){
                    //jiraComment body: 'Build succeeded', issueKey: 'EW-14'
                    jiraAssignIssue idOrKey: 'EW-14', userName: 'adityajain3896'
                }
            }
            else if(currentBuild.previousBuild.result=='FAILURE'){
                def transitionInput =
                [
                    transition: [
                        id: '41'
                    ]
                ]
                //jiraTransitionIssue idOrKey:"EW-13", input: transitionInput
                //jiraComment body: 'Build succeeded', issueKey: 'EW-13' 
            }
        }
   }
   
} 

