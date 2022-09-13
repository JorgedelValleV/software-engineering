public class SAMensajeImp implements SAMensaje {
    public boolean darDeBaja(int id) {
    	DAOMensaje d=new DAOMensajeImp();
    	TMensaje m= d.busca(id);
    	if(m.getTAutobus().isEmpty()) {
    		d.delete(m);
    		return true;
    	}
    	else return false;
    }

}
