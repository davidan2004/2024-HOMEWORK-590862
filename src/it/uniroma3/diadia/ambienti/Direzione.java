package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD(){
		@Override
		public Direzione getOpposta() {
			return SUD;
		}
	},
	EST(){
		@Override
		public Direzione getOpposta() {
			return OVEST;
		}
	},
	SUD(){
		@Override
		public Direzione getOpposta() {
			return NORD;
		}
	},
	OVEST(){
		@Override
		public Direzione getOpposta() {
			return EST;
		}
	};
	
	public abstract Direzione getOpposta();
}
