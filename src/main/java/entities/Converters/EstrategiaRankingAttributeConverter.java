package entities.Converters;

import entities.ranking.EstrategiaRanking;
import entities.ranking.RankingSegunCantidad;
import entities.ranking.RankingSegunImpacto;
import entities.ranking.RankingSegunTiempo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EstrategiaRankingAttributeConverter implements AttributeConverter <EstrategiaRanking, String> {
    @Override
    public String convertToDatabaseColumn(EstrategiaRanking estrategiaRanking) {
        return estrategiaRanking == null ? null : estrategiaRanking.toString();
    }

    @Override
    public EstrategiaRanking convertToEntityAttribute(String estrategiaRanking) {
        return estrategiaRanking == null ? null : this.toEstrategiaRanking(estrategiaRanking);
    }

    private EstrategiaRanking toEstrategiaRanking(String s) {
        EstrategiaRanking estrategiaRanking = null;
        if(s.equals("SegunTiempo")){
            estrategiaRanking = new RankingSegunTiempo();
        }
        if(s.equals("SegunImpacto")){
            estrategiaRanking = new RankingSegunImpacto();
        }
        if(s.equals("SegunCantidad")){
            estrategiaRanking = new RankingSegunCantidad();
        }
        return estrategiaRanking;
    }


}
