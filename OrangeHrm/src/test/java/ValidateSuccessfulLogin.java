import org.testng.annotations.Test;

import util.BaseTestCase;

public class ValidateSuccessfulLogin extends BaseTestCase {

	@Test
	public void Can_Successful_Login() {
		openOrangeHrmWithDefault();
		System.out.println("Successfuly logged in");
	}

}
