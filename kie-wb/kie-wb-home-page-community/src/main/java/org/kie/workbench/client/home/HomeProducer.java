/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.kie.workbench.client.home;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import org.jbpm.dashboard.renderer.service.DashboardURLBuilder;
import org.kie.workbench.client.resources.i18n.HomePageCommunityConstants;
import org.kie.workbench.common.screens.home.model.HomeModel;
import org.kie.workbench.common.screens.home.model.ModelUtils;
import org.kie.workbench.common.screens.home.model.Section;
import org.kie.workbench.common.screens.home.model.SectionEntry;
import org.guvnor.common.services.shared.security.KieWorkbenchACL;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.mvp.Command;

import static org.kie.workbench.common.workbench.client.menu.KieWorkbenchFeatures.*;

/**
 * Producer method for the Home Page content
 */
@ApplicationScoped
public class HomeProducer {

    private HomePageCommunityConstants constants = HomePageCommunityConstants.INSTANCE;

    private HomeModel model;

    @Inject
    private PlaceManager placeManager;

    @Inject
    private KieWorkbenchACL kieACL;

    public void init() {
        final String url = GWT.getModuleBaseURL();
        model = new HomeModel( constants.homeTheKnowledgeLifeCycle() );
        model.addCarouselEntry( ModelUtils.makeCarouselEntry( constants.homeDiscover(),
                                                              constants.homeDiscoverCaption(),
                                                              url + "/images/HandHome.jpg" ) );
        model.addCarouselEntry( ModelUtils.makeCarouselEntry( constants.homeAuthor(),
                                                              constants.homeAuthorCaption(),
                                                              url + "/images/HandHome.jpg" ) );
        model.addCarouselEntry( ModelUtils.makeCarouselEntry( constants.homeDeploy(),
                                                              constants.homeDeployCaption(),
                                                              url + "/images/HandHome.jpg" ) );
        model.addCarouselEntry( ModelUtils.makeCarouselEntry( constants.homeWork(),
                                                              constants.homeWorkCaption(),
                                                              url + "/images/HandHome.jpg" ) );
        model.addCarouselEntry( ModelUtils.makeCarouselEntry( constants.homeImprove(),
                                                              constants.homeImproveCaption(),
                                                              url + "/images/HandHome.jpg" ) );

        final Section s1 = new Section( constants.Authoring() );

        SectionEntry s1_a = ModelUtils.makeSectionEntry( constants.Project_Authoring(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "AuthoringPerspective" );
                                                             }
                                                         } );

        SectionEntry s1_b = ModelUtils.makeSectionEntry( constants.Contributors(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "ContributorsPerspective" );
                                                             }
                                                         } );

        SectionEntry s1_d = ModelUtils.makeSectionEntry( constants.artifactRepository(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "org.guvnor.m2repo.client.perspectives.GuvnorM2RepoPerspective" );
                                                             }
                                                         } );

        SectionEntry s1_e = ModelUtils.makeSectionEntry( constants.Administration(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "AdministrationPerspective" );
                                                             }
                                                         } );

        final Section s2 = new Section( constants.Deploy() );

        SectionEntry s2_a = ModelUtils.makeSectionEntry( constants.Process_Deployments(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "Deployments" );
                                                             }
                                                         } );

        SectionEntry s2_b = ModelUtils.makeSectionEntry( constants.Rule_Deployments(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "ServerManagementPerspective" );
                                                             }
                                                         } );

        SectionEntry s2_c = ModelUtils.makeSectionEntry( constants.Jobs(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "Jobs" );
                                                             }
                                                         } );

        final Section s3 = new Section( constants.Process_Management() );

        SectionEntry s3_a = ModelUtils.makeSectionEntry( constants.Process_Definitions(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "Process Definitions" );
                                                             }
                                                         } );

        SectionEntry s3_b = ModelUtils.makeSectionEntry( constants.Process_Instances(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "DataSet Process Instances With Variables" );
                                                             }
                                                         } );

        final Section s4 = new Section( constants.Tasks() );

        SectionEntry s4_a = ModelUtils.makeSectionEntry( constants.Tasks_List(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "DataSet Tasks" );
                                                             }
                                                         } );

        final Section s5 = new Section( constants.Dashboards() );

        SectionEntry s5_a = ModelUtils.makeSectionEntry( constants.Process_Dashboard(),
                                                         new Command() {

                                                             @Override
                                                             public void execute() {
                                                                 placeManager.goTo( "DashboardPerspective" );
                                                             }
                                                         } );

        final String dashbuilderURL = DashboardURLBuilder.getDashboardURL( "/dashbuilder/workspace", "showcase", LocaleInfo.getCurrentLocale().getLocaleName() );
        SectionEntry s5_b = ModelUtils.makeSectionEntry( constants.Business_Dashboard(),
                                                         new Command() {
                                                             @Override
                                                             public void execute() {
                                                                 Window.open( dashbuilderURL, "_blank", "" );
                                                             }
                                                         } );

        s1.setRoles( kieACL.getGrantedRoles( G_AUTHORING ) );
        s1_a.setRoles( kieACL.getGrantedRoles( F_PROJECT_AUTHORING ) );
        s1_b.setRoles( kieACL.getGrantedRoles( F_CONTRIBUTORS ) );
        s1_d.setRoles( kieACL.getGrantedRoles( F_ARTIFACT_REPO ) );
        s1_e.setRoles( kieACL.getGrantedRoles( F_ADMINISTRATION ) );

        s2.setRoles( kieACL.getGrantedRoles( G_DEPLOY ) );
        s2_a.setRoles( kieACL.getGrantedRoles( F_DEPLOYMENTS ) );
        s2_b.setRoles( kieACL.getGrantedRoles( F_MANAGEMENT ) );
        s2_c.setRoles( kieACL.getGrantedRoles( F_JOBS ) );

        s3.setRoles( kieACL.getGrantedRoles( G_PROCESS_MANAGEMENT ) );
        s3_a.setRoles( kieACL.getGrantedRoles( F_PROCESS_DEFINITIONS ) );
        s3_b.setRoles( kieACL.getGrantedRoles( F_PROCESS_INSTANCES ) );

        s4.setRoles( kieACL.getGrantedRoles( G_TASKS ) );
        s4_a.setRoles( kieACL.getGrantedRoles( F_TASKS ) );

        s5.setRoles( kieACL.getGrantedRoles( G_DASHBOARDS ) );
        s5_a.setRoles( kieACL.getGrantedRoles( F_PROCESS_DASHBOARD ) );
        s5_b.setRoles( kieACL.getGrantedRoles( F_DASHBOARD_BUILDER ) );

        s1.addEntry( s1_a );
        s1.addEntry( s1_b );
        s1.addEntry( s1_d );
        s1.addEntry( s1_e );

        s2.addEntry( s2_a );
        s2.addEntry( s2_b );
        s2.addEntry( s2_c );

        s3.addEntry( s3_a );
        s3.addEntry( s3_b );

        s4.addEntry( s4_a );

        s5.addEntry( s5_a );
        s5.addEntry( s5_b );

        model.addSection( s1 );
        model.addSection( s2 );
        model.addSection( s3 );
        model.addSection( s4 );
        model.addSection( s5 );
    }

    @Produces
    public HomeModel getModel() {
        return model;
    }

}
