import React from 'react';
  
const headerStyle = {

    position: 'absolute',
    fontFamily: 'Open Sans',
    fontSize: '20px',
    fontWeight: '600',
    top: '200px',
    marginLeft: '200px',
    marginRight: '200px',
    color : "white"
    
 
    
};

function About () {
    return <div>
        <h2 style = {headerStyle}>What is Phishing? Phishing is when bad actors use a fake account from a position of authority to get access to your private data. They come in the form of a fake email from your bank, employer, or IT department and usually ask for login information in order to steal your account from you. We here at the Phish Initiative have created a program intended to test the ability of your employees to spot a phishing attempt.!</h2>
    </div>
}
export default About;