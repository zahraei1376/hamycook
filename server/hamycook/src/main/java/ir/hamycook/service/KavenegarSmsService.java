package ir.hamycook.service;

import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.excepctions.ApiException;
import com.kavenegar.sdk.excepctions.HttpException;
import com.kavenegar.sdk.models.SendResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KavenegarSmsService {

    @Value("${apikey}")
    private String apiKey;

    @Value("${sender}")
    private String sender;

    public void sendOptCode(String phoneNumber, String otpCode) {
        try {
            System.out.println("apikey: " + apiKey);
            System.out.println("sender: " + sender);
            KavenegarApi kavenegarApi = new KavenegarApi("4A41794468464A677357672B366D7777594542786150614A38436C5A4D7A52377563734449577745724F453D");
            SendResult sendResult = kavenegarApi.send("1000596446", phoneNumber, otpCode);

        } catch (HttpException e) {
            System.out.println("HttpException: " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("ApiException: " + e.getMessage());
        }
    }

}
