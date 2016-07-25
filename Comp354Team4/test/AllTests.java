

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ActivityTests.*;
import ProjectTests.*;
import GeneralApplicationTests.*;
import ResourceTests.*;
import UserTests.*;
import PropertyTests.*;


@RunWith(Suite.class)
@SuiteClasses({ TestCreateActivity.class, TestDeleteActivity.class, TestUpdateActivity.class,TestActivityDependency.class,
	TestCreateProject.class, TestDeleteProject.class, TestUpdateProject.class,
	TestCreateProperty.class,TestEditProperty.class, TestDeleteProperty.class,
	TestCreateResource.class, TestDeleteResource.class,
	TestCreateUser.class, TestUserMembers.class ,
	TestLogin.class, TestDatabaseConnection.class,   })
public class AllTests {

}
