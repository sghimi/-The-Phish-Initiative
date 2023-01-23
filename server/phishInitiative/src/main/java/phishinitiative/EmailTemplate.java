package phishinitiative;

/**
 *
 * @author Number
 */
public class EmailTemplate {
	
	public static EmailTemplate[] templates = {
	new EmailTemplate(0),
	new EmailTemplate(1),
	new EmailTemplate(2),
	new EmailTemplate(3)
	};
	
	
	public String title;
	public String body1;
	public String body2;
	public String linkBase;
	public String sender;
	
	private EmailTemplate(int type){
		switch(type){
                        //case 0: LSU
			case 0:
				this.title =    "Your LSU Account has had an Error!";
				this.body1 =    "<html><p>Dear LSU Student,</p><p>We have noticed an error associated with your account. The server has been unable to verify your login and associate data with your account.</p><p>Many different elements, such as grades, credit hours, and personal information could be corrupted or deleted.</p><p>Please Sign in at the link <a href=";
				this.body2 =    " >here</a> and follow the instructions to fix any errors associated with your account.</p><p>Do so quickly, or data could be lost forever.</p></html>";
				//The Quotation marks before and after the link 
                                //are not included in Body1 or Body2 currently
                                this.linkBase = "";
				this.sender =   "noreply@notlsu.edu";
				
				break;
                        //case 1: Banking
			case 1:
				this.title = "Banking Account Compromised";
				this.body1 = "<html><p>Please immediately sign in <a href=";
				this.body2 = " >at this link.</a></p><p>Your banking account has been breached.</p><p>Your data has been listed as publically available and multiple unauthorized purchases have been made using your information.</p><p>If you do not sign in to fix the issue:</p><p>- Any further transactions may not be denied.</p><p>- You will not be compensated for lost funds.</p><p>- A $5,000 charge will be placed on your account for cybersecurity negligence.</p></html>";
                                //The Quotation marks before and after the link 
                                //are not included in Body1 or Body2 currently
				this.linkBase = "";
				this.sender = "noreply@victorybanking.com";
                                
				break;
/*
                        //case 2: Corporate
			case 2:
				this.title = "Moving Server (Mandatory Sign-In Required)";
				this.body1 = "<html><p>We have decided to update our website and move servers.</p><p>As a result, we are requiring all employess to sign in at the link <a href=";
				this.body2 = " >here.</a></p><p>There are only three days remaining until the previous server shuts down. Sign in at the new website to move accounts or work related data may be lost.</p><p>Data that may be lost includes:</p><p>- payment information</p><p>- scheduling</p><p>- dates/times of previous workdays</p><p>Failure to sign-in on the new website may result in your worked hours being unable to be calculated. You may not be compensated for lost hours. </p></html>";
                                //The Quotation marks before and after the link 
                                //are not included in Body1 or Body2 currently
				this.linkBase = "";
				this.sender = "noreply@websignin.org";
                                
				break;
*/
                        //case 2: Twitter
			case 2:
				this.title = "Your Twitter Account is at Risk!";
				this.body1 = "<html><p>Twitter noticed a failed attempt at logging into your account.</p><p>Location services indicate the login attempt was made yesterday in:</p><p> - New Delhi, India [3:23AM]</p><p>Sign in <a href=";
				this.body2 = " >here</a> if this login attempt wasn't made by you. Otherwise, it won't be flagged and will be deemed legitimate. Legitimate login attemps give the user full access to the account.</p</html>";
				this.linkBase = "";
				this.sender = "noreply@tweeterbird.com";
                                
				break;
                        //case 3: Netflix
			case 3:
				this.title = "Free Netflix Giveaway!";
				this.body1 = "<html><p>! NETFLIX NOTICE ! NETFLIX NOTICE !</p><p>If you sign in at <a href=";
				this.body2 = " >this link</a> you can get Netflix for free!</p><p>Due to your continued use of our platform, we are offering you free access to Netflix for life.</p><p>Check out the link and let us help you out for your continued support.</p></html>";
				this.linkBase = "";
				this.sender = "noreply@networkflicks.com";
                                
				break;
/*
                        //case 4: Instagram
			case 4:
				this.title = "Your Instagram Photos are at Risk!";
				this.body1 = "<html><p>Instagram is planning to shut down in three weeks.</p><p>We are allowing users to access their accounts and save their photos before the shutdown.</p><p>Services may be shut down prematurely, so act fast and save your photos before they are lost forever. Access your account in recovery mode for the ability to save all photos on your account at once.</p><p><a href=";
				this.body2 = " >Click here to access your account in recovery mode.</a></p><p>We apologize for the short notice.</p></html>";
				this.linkBase = "";
				this.sender = "noreply@instantgrammers.com";
                                
				break;
*/  
		}

	}

}