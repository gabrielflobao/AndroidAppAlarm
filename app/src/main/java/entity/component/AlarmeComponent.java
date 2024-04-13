package entity.component;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.EnumNiveis;
import model.Alarme;
import utils.InputDateParser;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class AlarmeComponent {


    private EditText inputNome;
    private Spinner sNivel;
    private EditText eHoraAlarme;
    private RadioButton rAtivo;
    private RadioButton rInativo;
    private CheckBox cDiasUteis;
    private RadioButton rOpcao;
    private final String regexHoraMinuto = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
    Alarme entity = new Alarme();

    public AlarmeComponent(EditText inputNome,
                           Spinner sNivel,
                           EditText eHoraAlarme,
                           RadioButton rAtivo,
                           RadioButton rInativo,
                           CheckBox cDiasUteis,
                           RadioButton rOpcao) {
        this.inputNome = inputNome;
        this.sNivel = sNivel;
        this.eHoraAlarme = eHoraAlarme;
        this.rAtivo = rAtivo;
        this.rInativo = rInativo;
        this.cDiasUteis = cDiasUteis;
        this.rOpcao = rOpcao;
    }


    public Boolean isOpAtivoInativoUnChecked() {
        return (!rInativo.isChecked() && !rAtivo.isChecked());
    }


    public Boolean isNomeEmpty() {
        return inputNome.getText().length() == 0;
    }

    public Boolean isHoraAlarmeEmpty() {
        return eHoraAlarme.getText().length() == 0;
    }

    public Boolean validateHoraMinuto() {
        Pattern pattern = Pattern.compile(regexHoraMinuto);
        Matcher matcher = pattern.matcher(this.eHoraAlarme.getText());
        return matcher.matches();
    }

    public Alarme generateEntity(Context context) {

        try {
            Alarme alarme = new Alarme();
            alarme.setAtivo(rAtivo.isChecked());
            alarme.setNome(inputNome.getText().toString());
            alarme.setDiasUteis(cDiasUteis.isChecked());
            alarme.setNiveis(EnumNiveis.getNivel(sNivel.getSelectedItem().toString().toUpperCase()));
            alarme.setHora(InputDateParser.getTimeAlarme(eHoraAlarme));
            return alarme;
        } catch (DateTimeParseException e) {
            Toast message = Toast.makeText(context, "Campo Hora segue a seguinte máscara : HH:mm" + e.getMessage(), Toast.LENGTH_LONG);
            message.show();
            return null;
        }


    }


}
