/*
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package com.consol.citrus.selenium.config.xml;

import com.consol.citrus.selenium.actions.AbstractSeleniumAction;
import com.consol.citrus.selenium.actions.FindElementAction;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * @author Tamer Erdogan, Christoph Deppisch
 * @since 2.7
 */
public class FindElementActionParser extends AbstractBrowserActionParser {

    @Override
    protected void parseAction(BeanDefinitionBuilder beanDefinition, Element element, ParserContext parserContext) {
        Element target = DomUtils.getChildElementByTagName(element, "element");
        if (target != null) {
            String selector = null;
            String selectorType = null;

            if (target.hasAttribute("id")) {
                selectorType = "id";
                selector = target.getAttribute("id");
            } else if (target.hasAttribute("name")) {
                selectorType = "name";
                selector = target.getAttribute("name");
            } else if (target.hasAttribute("tag-name")) {
                selectorType = "tag-name";
                selector = target.getAttribute("tag-name");
            } else if (target.hasAttribute("class-name")) {
                selectorType = "class-name";
                selector = target.getAttribute("class-name");
            } else if (target.hasAttribute("css-selector")) {
                selectorType = "css-selector";
                selector = target.getAttribute("css-selector");
            } else if (target.hasAttribute("link-text")) {
                selectorType = "link-text";
                selector = target.getAttribute("link-text");
            } else if (target.hasAttribute("xpath")) {
                selectorType = "xpath";
                selector = target.getAttribute("xpath");
            }

            beanDefinition.addPropertyValue("selectorType", selectorType);
            beanDefinition.addPropertyValue("select", selector);
        }
    }

    @Override
    protected Class<? extends AbstractSeleniumAction> getBrowserActionClass() {
        return FindElementAction.class;
    }
}