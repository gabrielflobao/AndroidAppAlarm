package entity.component;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.time.LocalTime;

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
        return inputNome.getText().length() ==0;
    }

    public Boolean isHoraAlarmeEmpty() {
        return eHoraAlarme.getText().length() ==0;
    }

    public Alarme generateEntity() {
        Alarme alarme = new Alarme();
        alarme.setAtivo(rAtivo.isChecked());
        alarme.setNome(inputNome.getText().toString());
        alarme.setDiasUteis(cDiasUteis.isChecked());
        alarme.setNiveis(EnumNiveis.getNivel(sNivel.getSelectedItem().toString().toUpperCase()));
        alarme.setHora(InputDateParser.getTimeAlarme(eHoraAlarme));
        return alarme;

    }
}
