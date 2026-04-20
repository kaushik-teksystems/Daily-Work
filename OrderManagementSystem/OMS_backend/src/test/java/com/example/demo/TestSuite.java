package com.example.demo;

import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("MyTestSuite")
@IncludePackages("com.example.demo")
@SelectPackages("com.example.demo")
public class TestSuite {

}
