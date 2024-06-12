package it.uniroma3.diadia.giocatore;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa {
	private int pesoMax;
	private List<Attrezzo> attrezzi;
	
	public Borsa() {
	Properties properties = new Properties();
	try{properties.load(this.getClass().getClassLoader().getResourceAsStream("diadia.properties"));
	} catch(IOException e) {
		e.printStackTrace();
	}
	this.attrezzi = new ArrayList<>();
	this.pesoMax =  Integer.valueOf(properties.getProperty("peso_max_borsa"));
	}
	
	public Borsa(int pesoMax) {
	this.attrezzi = new ArrayList<>();
	this.pesoMax = pesoMax;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
	if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
	return false;
		this.attrezzi.add(attrezzo);
	return true;
	}
	
	public int getPesoMax() {
	return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a : this.attrezzi)
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		return null;
	}	
	
	public int getPeso() {
		int peso = 0;
		for(Attrezzo a : this.attrezzi)
			peso += a.getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return attrezzi.size() == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a;
		Iterator<Attrezzo>  it = this.attrezzi.iterator();
		while(it.hasNext()) {
			a = it.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				it.remove();
				return a;
			}
		}
		return null;
	}
		
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		for (Attrezzo a : this.attrezzi)
		s.append(a.toString()+" ");
		}
		else
		s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> out = new ArrayList<Attrezzo>(this.attrezzi);
		Collections.sort(out, new ComparatoreAttrezziPerPeso());
		return out;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> out = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		out.addAll(attrezzi);
		return out;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> out = new HashMap<>();
		
		for(Attrezzo a : this.attrezzi) {
			Set<Attrezzo> set = out.get(a.getPeso());
			if(set == null)
				set = new HashSet<>();
			set.add(a);
			out.put(a.getPeso(), set);
		}
		
		return out;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		final SortedSet<Attrezzo> out = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		out.addAll(this.attrezzi);
		return out;
	}
		
}


