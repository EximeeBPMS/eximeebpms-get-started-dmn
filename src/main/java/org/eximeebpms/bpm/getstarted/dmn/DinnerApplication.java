/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eximeebpms.bpm.getstarted.dmn;

import org.eximeebpms.bpm.application.PostDeploy;
import org.eximeebpms.bpm.application.ProcessApplication;
import org.eximeebpms.bpm.application.impl.JakartaServletProcessApplication;
import org.eximeebpms.bpm.dmn.engine.DmnDecisionTableResult;
import org.eximeebpms.bpm.engine.DecisionService;
import org.eximeebpms.bpm.engine.ProcessEngine;
import org.eximeebpms.bpm.engine.variable.VariableMap;
import org.eximeebpms.bpm.engine.variable.Variables;

import java.util.logging.Level;
import java.util.logging.Logger;

@ProcessApplication("Dinner App DMN")
public class DinnerApplication extends JakartaServletProcessApplication
{
    protected final static Logger LOGGER = Logger.getLogger(DinnerApplication.class.getName());

    @PostDeploy
    public void evaluateDecisionTable(ProcessEngine processEngine) {

        DecisionService decisionService = processEngine.getDecisionService();

        VariableMap variables = Variables.createVariables()
                .putValue("season", "Spring")
                .putValue("guestCount", 10);

        DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("dish", variables);
        String desiredDish = dishDecisionResult.getSingleEntry();

        LOGGER.log(Level.INFO, "\n\nDesired dish: {0}\n\n", desiredDish);
    }

}
