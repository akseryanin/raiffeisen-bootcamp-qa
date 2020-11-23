package ru.stqa;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class HW_TASK7_FireFox extends HW_TASK7{
    @Before
    @Override
    public void start() {
        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver = new FirefoxDriver(options);
    }

    @Override
    protected void registration(String email,String password){
            //переходим на страницу регистрации
        driver.findElement(By.cssSelector("#box-account-login a")).click();

        driver.findElement(By.name("tax_id")).sendKeys("123456789");
        driver.findElement(By.name("company")).sendKeys("HSE");
        sleep(500);
        driver.findElement(By.name("firstname")).sendKeys("Alexander");
        driver.findElement(By.name("lastname")).sendKeys("Seryanin");
        sleep(500);
        driver.findElement(By.name("address1")).sendKeys("Moscow Polyani 7");
        driver.findElement(By.name("address2")).sendKeys("Moscow ul Skobelebckayz d 1");
        sleep(500);
        driver.findElement(By.name("postcode")).sendKeys("11111");
        driver.findElement(By.name("city")).sendKeys("Moscow");
        sleep(500);
        driver.findElement(By.cssSelector("td span.select2-selection__arrow")).click();
        WebElement searchCountryField = driver.findElement(By.cssSelector("input.select2-search__field"));
        searchCountryField.sendKeys("United States");
        searchCountryField.sendKeys(Keys.ENTER);
        wait.until(textToBePresentInElementLocated(By.cssSelector("select[name=zone_code]"),"Alabama"));
            //Select zone = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
            //zone.selectByValue("AK");
        driver.findElement(By.cssSelector("select[name=zone_code]")).click();
        WebElement searchZonesField = driver.findElement(By.cssSelector("select[name=zone_code]"));
        searchZonesField.sendKeys("AK");
        searchZonesField.sendKeys(Keys.ENTER);
        sleep(500);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+19268472679");
        sleep(500);
        driver.findElement(By.name("newsletter")).click();
        sleep(500);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
            //найдем кнопку logout
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account li:last-child")));
    }
    @Override
    protected void login(String email,String password){
        sleep(500);
        wait.until(presenceOfElementLocated(By.name("email")));
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account li:last-child")));
    }

    @Override
    protected void logout(){
        sleep(500);
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account li:last-child a")));
        driver.findElement(By.cssSelector("#box-account li:last-child a")).click();
        wait.until(presenceOfElementLocated(By.name("email")));
    }

}
/*
1606160044293	geckodriver	INFO	Listening on 127.0.0.1:19267
1606160044949	mozrunner::runner	INFO	Running command: "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe" "--marionette" "-foreground" "-no-remote" "-profile" "C:\\Users\\sasha\\AppData\\Local\\Temp\\rust_mozprofilejkgSzm"
Can't find symbol 'eglSwapBuffersWithDamageEXT'.
JavaScript error: resource://gre/modules/XULStore.jsm, line 66: Error: Can't find profile directory.
console.warn: SearchSettings: "get: No settings file exists, new profile?" (new Error("", "(unknown module)"))
1606160048303	Marionette	INFO	Listening on port 54272
1606160048590	Marionette	WARN	TLS certificate errors will be ignored for this session
нояб. 23, 2020 10:34:08 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: W3C
1606160053093	Marionette	INFO	Stopped listening on port 54272

###!!! [Child][RunMessage] Error: Channel closing: too late to send/recv, messages will be lost


###!!! [Child][MessageChannel::SendAndWait] Error: Channel error: cannot send/recv


java.lang.NullPointerException
	at ru.stqa.HW_TASK7_FireFox.registration(HW_TASK7_FireFox.java:43)
	at ru.stqa.HW_TASK7.regist(HW_TASK7.java:32)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:110)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:38)
	at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:62)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:51)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
	at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:94)
	at com.sun.proxy.$Proxy2.processTestClass(Unknown Source)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.processTestClass(TestWorker.java:119)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.remote.internal.hub.MessageHubBackedObjectConnection$DispatchWrapper.dispatch(MessageHubBackedObjectConnection.java:182)
	at org.gradle.internal.remote.internal.hub.MessageHubBackedObjectConnection$DispatchWrapper.dispatch(MessageHubBackedObjectConnection.java:164)
	at org.gradle.internal.remote.internal.hub.MessageHub$Handler.run(MessageHub.java:414)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
	at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:56)
	at java.base/java.lang.Thread.run(Thread.java:832)


 */