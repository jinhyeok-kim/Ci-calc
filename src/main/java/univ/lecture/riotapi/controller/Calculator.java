package univ.lecture.riotapi.controller;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;


/**
 * Created by tchi on 2017. 3. 19..
 */
/*
 * Practice Coding Team3
 * 201302387 김진혁
 * 201302491 최광호
 * 201302476 전성배
 * 201302467 이충완
 * 
 * */
public class Calculator {
    public int calculate(String exp) {

	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine engine = mgr.getEngineByName("JavaScript");

	try {
		return (int)engine.eval(exp);
	} catch (ScriptException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
    }
}
