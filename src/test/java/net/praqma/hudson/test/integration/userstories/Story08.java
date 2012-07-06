package net.praqma.hudson.test.integration.userstories;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.FreeStyleProject;
import hudson.model.Result;
import hudson.model.TaskListener;
import hudson.scm.PollingResult;
import net.praqma.clearcase.ucm.entities.Baseline;
import net.praqma.clearcase.ucm.entities.Project.PromotionLevel;
import net.praqma.hudson.scm.CCUCMScm;
import net.praqma.hudson.test.CCUCMRule;
import net.praqma.util.debug.Logger;

import net.praqma.clearcase.test.annotations.ClearCaseUniqueVobName;
import net.praqma.clearcase.test.junit.ClearCaseRule;

import static org.junit.Assert.*;

public class Story08 {

	@ClassRule
	public static CCUCMRule jenkins = new CCUCMRule();
	
	@Rule
	public static ClearCaseRule ccenv = new ClearCaseRule( "ccucm-story08" );

	private static Logger logger = Logger.getLogger();

	@Test
	@ClearCaseUniqueVobName( name = "story08a" )
	public void story08a() throws Exception {
		/* Flip promotion level */
		Baseline baseline = ccenv.context.baselines.get( "model-1" );
		baseline.setPromotionLevel( PromotionLevel.REJECTED );
		
		AbstractBuild<?, ?> build = jenkins.initiateBuild( "story08a", "child", "_System@" + ccenv.getPVob(), "one_int@" + ccenv.getPVob(), false, false, false, false, false );

		/* Build validation */
		assertTrue( build.getResult().isBetterOrEqualTo( Result.NOT_BUILT ) );
		
		/* Expected build baseline */
		Baseline buildBaseline = jenkins.getBuildBaselineNoAssert( build );
		assertNull( buildBaseline );
		
		Baseline createdBaseline = jenkins.getCreatedBaseline( build );
		assertNull( createdBaseline );
	}
	
	@Test
	@ClearCaseUniqueVobName( name = "story08b" )
	public void story08b() throws Exception {
		/* Flip promotion level */
		Baseline baseline = ccenv.context.baselines.get( "model-1" );
		baseline.setPromotionLevel( PromotionLevel.INITIAL );
		
		/* First build must succeed to get a workspace */
		AbstractBuild<?, ?> build = jenkins.initiateBuild( "story08b", "child", "_System@" + ccenv.getPVob(), "one_int@" + ccenv.getPVob(), false, false, false, false, false );
		AbstractProject<?, ?> project = build.getProject();
		assertTrue( build.getResult().isBetterOrEqualTo( Result.SUCCESS ) );		
				
		TaskListener listener = jenkins.createTaskListener();
		PollingResult result = project.poll( listener );

		/* Polling result validation */
		assertEquals( PollingResult.NO_CHANGES, result );
		
	}
		
}
