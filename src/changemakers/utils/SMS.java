/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author zaiir
 */
public class SMS {

      // Find your Account Sid and Token at console.twilio.com
  public static final String ACCOUNT_SID = "AC9af821631e0d1bcf1e20396b0db73e59";
  public static final String AUTH_TOKEN = "55304a11aa95a228bbde8cbfcae61570";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message;
      message = Message
              .creator(
                      new PhoneNumber("+21658310144"),
                      new PhoneNumber("+16813233319"),
                      "Votre Reclamation à été ajouté"
              )
              .create();

    System.out.println(message.getSid());
  }
}
    

