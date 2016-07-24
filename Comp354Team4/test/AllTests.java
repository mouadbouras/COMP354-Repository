

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ActivityTests.*;
import ProjectTests.*;
import GeneralApplicationTests.*;
import ResourceTests.*;

@RunWith(Suite.class)
@SuiteClasses({ TestCreateActivity.class, TestDeleteActivity.class, TestUpdateActivity.class,
	TestCreateProject.class, TestDeleteProject.class, TestUpdateProject.class, TestCreateResource.class,
	TestLogin.class, TestDatabaseConnection.class})
public class AllTests {

}
