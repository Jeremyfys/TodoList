package com.petrociencia.jeremy.com.petrociencia;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;


@RunWith(AndroidJUnit4.class)
public class AppTest {

    @Rule
    public ActivityTestRule<Login> mActivityTestRule = new ActivityTestRule(Login.class);

    @Test
    public void loginTestAdmin() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("admin"));
        onView(withId(R.id.inputPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Registro.class.getName()));
        Intents.release();

    }

    @Test
    public void loginTest() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("jeremyfys"));
        onView(withId(R.id.inputPassword)).perform(typeText("24241182"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Lista.class.getName()));
        Intents.release();

    }
    @Test
    public void loginTestFailed() {

        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("jeremyfys"));
        onView(withId(R.id.inputPassword)).perform(typeText("24241182"));
        onView(withId(R.id.buttonlogin)).perform(click());
        onView(withText("El Usuario y/o Contraseña es incorrecto")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();
    }

    @Test
    public void CrearNota() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("Marco"));
        onView(withId(R.id.inputPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Lista.class.getName()));
        onView(withId(R.id.action_add_task)).perform(click());
        onView(withText("Que haras despues?")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(supportsInputMethods()).perform(typeText("Marco"));
        onView(withText("Agregar")).perform(click());
        onView(withText("Nota creada")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }


    @Test
    public void EditarNota() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("Marco"));
        onView(withId(R.id.inputPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Lista.class.getName()));
        onData(anything()).inAdapterView(withId(R.id.list_todo)).atPosition(0).onChildView(withId(R.id.task_edit)).perform(click());
        onView(supportsInputMethods()).perform(typeText("Cambio"));
        onView(withText("Editar")).perform(click());
        onView(withText("Nota editada")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }

    @Test
    public void EliminarNota() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("Marco"));
        onView(withId(R.id.inputPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Lista.class.getName()));
        onData(anything()).inAdapterView(withId(R.id.list_todo)).atPosition(1).onChildView(withId(R.id.task_delete)).perform(click());
        onView(withText("Nota eliminada")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }

    @Test
    public void TerminarNota() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("Marco"));
        onView(withId(R.id.inputPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Lista.class.getName()));
        onData(anything()).inAdapterView(withId(R.id.list_todo)).atPosition(2).onChildView(withId(R.id.task_confirm)).perform(click());
        onView(withText("Tarea Finalizada")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }
    @Test
    public void Registro() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("admin"));
        onView(withId(R.id.inputPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Registro.class.getName()));

        onView(withId(R.id.inputNewUser)).perform(typeText("Marco"));
        onView(withId(R.id.inputNewPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonRegistrar)).perform(click());
        onView(withText("Se he creado un nuevo usuario 1")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }

    @Test
    public void RegistroVacio() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("admin"));
        onView(withId(R.id.inputPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Registro.class.getName()));
        onView(withId(R.id.inputNewUser)).perform(typeText(""));
        onView(withId(R.id.inputNewPassword)).perform(typeText(""));
        onView(withId(R.id.buttonRegistrar)).perform(click());
        onView(withText("Rellene los campos Usuario y Contraseña")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }

    @Test
    public void RegistroRepetidoUser() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("admin"));
        onView(withId(R.id.inputPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Registro.class.getName()));
        onView(withId(R.id.inputNewUser)).perform(typeText("Pedro"));
        onView(withId(R.id.inputNewPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonRegistrar)).perform(click());
        onView(withText("El usuario ya existe")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Intents.release();

    }

    @Test
    public void SalirRegistro() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("admin"));
        onView(withId(R.id.inputPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Registro.class.getName()));
        onView(withId(R.id.SalirRegistro)).perform(click());
        Intents.release();
    }

    @Test
    public void SalirLista() {
        Intents.init();
        onView(withId(R.id.inputUsername)).perform(typeText("Marco"));
        onView(withId(R.id.inputPassword)).perform(typeText("123456"));
        onView(withId(R.id.buttonlogin)).perform(click());
        intended(hasComponent(Lista.class.getName()));
        onView(withId(R.id.action_salir)).perform(click());
        Intents.release();




    }





}
