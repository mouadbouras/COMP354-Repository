

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ActivityTests.*;
import ProjectTests.*;
import GeneralApplicationTests.*;

@RunWith(Suite.class)
@SuiteClasses({ TestCreateActivity.class, TestDeleteActivity.class, TestUpdateActivity.class, 
	TestCreateProject.class, TestDeleteProject.class, TestUpdateProject.class,
	TestLogin.class})
public class AllTests {

}
