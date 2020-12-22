package com.backend.magicarpe.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

public class Mail {

    private MailjetClient client;
    private MailjetRequest request;
    private MailjetResponse response;

    public void sendMail(String receiver) throws MailjetException, MailjetSocketTimeoutException {

        client = new MailjetClient("9fd06fdd1913b8c0205daf4cc4439906", "c51a49a5dd2a59d9d43af2fc9b2093aa", new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "guillaume.cirillo@epitech.eu")
                                        .put("Name", "Guillaume"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", receiver)
                                                .put("Name", "Guillaume")))
                                .put(Emailv31.Message.SUBJECT, "Merci de votre inscription.")
                                .put(Emailv31.Message.HTMLPART, "<h3>Bienvenue sur Magicarpe !!!!</h3><br />Merci de nous donner des points la Rafale :)!")
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
        response = client.post(request);
    }

}
