package ch.hslu.informatik.gastgewerbe.businessabrechnung;

import java.util.Date;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.api.AbrechnungService;
import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Tisch;

public class AbrechnungManager implements AbrechnungService {

    @Override
    public double tischAbrechnen(Tisch tisch) throws Exception {
        return 0;
    }

	@Override
	public double abschluss() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, Date datum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
