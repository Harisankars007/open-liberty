/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
if(!window.myfaces){var myfaces=new function(){};window.myfaces=myfaces;}myfaces._impl=(myfaces._impl)?myfaces._impl:{};myfaces._impl.core=(myfaces._impl.core)?myfaces._impl.core:{};if(!myfaces._impl.core._EvalHandlers){myfaces._impl.core._EvalHandlers=new function(){var _T=this;_T._evalExecScript=function(code){var _r=window.execScript(code);if("undefined"!=typeof _r&&_r=="null"){return null;}return _r;};_T._evalHeadAppendix=function(code){var _l=document.getElementsByTagName("head")[0]||document.documentElement;var _p=document.createElement("script");_p.type="text/javascript";_p.text=code;_l.insertBefore(_p,_l.firstChild);_l.removeChild(_p);return null;};_T._standardGlobalEval=function(code){var _U="undefined";var gEval=function(){var _r=window.eval.call(window,code);if(_U==typeof _r){return null;}return _r;};var _r=gEval();if(_U==typeof _r){return null;}return _r;};_T.globalEval=function(c){var _e="_evalType";var _w=window;var _b=myfaces._impl.core._Runtime.browser;if(!_T[_e]){_T[_e]=_w.execScript?"_evalExecScript":null;_T[_e]=_T[_e]||((_w.eval&&(!_b.isBlackBerry||_b.isBlackBerry>=6))?"_standardGlobalEval":null);_T[_e]=_T[_e]||((_w.eval)?"_evalHeadAppendix":null);}if(_T[_e]){return _T[_T[_e]](c);}eval.call(window,c);return null;};};}