/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.utils;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 *
 * @author laboratoriointerface
 */
public class ExtraHtmlTagRuleBundle implements TagRuleBundle{

    @Override
    public void install(State state, ContentProperty cp, SiteMeshContext smc) {
        //HTML5 tags
        state.addRule("header", new ExportTagToContentRule(smc, cp.getChild("header"), false));
        state.addRule("nav", new ExportTagToContentRule(smc, cp.getChild("nav"), false));
        state.addRule("content", new ExportTagToContentRule(smc, cp.getChild("content"), false));
        
    }

    @Override
    public void cleanUp(State state, ContentProperty cp, SiteMeshContext smc) {
        // Do nothing
    }
    
}
