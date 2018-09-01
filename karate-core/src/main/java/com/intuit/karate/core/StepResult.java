/*
 * The MIT License
 *
 * Copyright 2018 Intuit Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intuit.karate.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pthomas3
 */
public class StepResult  {

    private static final Map<String, Object> DUMMY_MATCH;
    
    private final Step step;
    private final Result result;
    private final String stepLog;

    static {
        DUMMY_MATCH = new HashMap(2);
        DUMMY_MATCH.put("location", "karate");
        DUMMY_MATCH.put("arguments", Collections.EMPTY_LIST);
    }

    private static Map<String, Object> docStringToMap(int line, String stepLog) {
        Map<String, Object> map = new HashMap(3);
        map.put("content_type", "");
        map.put("line", line);
        map.put("value", stepLog);
        return map;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap(6);
        map.put("line", step.getLine());
        map.put("keyword", step.getPrefix());
        map.put("name", step.getText());
        map.put("result", result.toMap());
        map.put("match", DUMMY_MATCH);
        if (stepLog != null) {
            map.put("doc_string", docStringToMap(step.getLine(), stepLog));
        }
        return map;
    }
        
    public boolean isStopped() {
        return result.isFailed() || result.isAborted();
    }    

    public StepResult(Step step, Result result, String stepLog) {
        this.step = step;
        this.result = result;
        this.stepLog = stepLog;
    }   

    public Step getStep() {
        return step;
    }        
    
    public Result getResult() {
        return result;
    }

    public String getStepLog() {
        return stepLog;
    }
    
}