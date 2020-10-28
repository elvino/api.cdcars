package com.cdcars.core.recommender.scopes.contextual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cdcars.infra.dataset.AmazonCrossDataset;

public class LocationCityContextualAttribute implements
		AbstractContextualAttribute {
	
	private String name;
	
	private static final String citiesFile = AmazonCrossDataset.folderResources+"/cities.txt";
	
	private static ArrayList<LocationCityContextualAttribute> cities = new ArrayList<LocationCityContextualAttribute>();
	
	static{
		LocationCityContextualAttribute teste = new LocationCityContextualAttribute();
	}

	@Override
	public String name() {
		return this.name;
	}

	private long code;
	
	private LocationCityContextualAttribute(String name, long code){
		this.name = name;
		this.code = code;
	}

	private LocationCityContextualAttribute() {
		
		BufferedReader reader = null;
		InputStreamReader streamReader = null;
		
		try {
			File fileEN = new File(citiesFile);

			FileInputStream stream;

			String line = "";
		
			stream = new FileInputStream(fileEN);

			streamReader = new InputStreamReader(stream);
			reader = new BufferedReader(streamReader);
			
			line = reader.readLine();
			
			while (line != null) {
				String cities[] = line.split(",");
				for (int i = 0; i < cities.length; i++) {
					String city = cities[i].trim();
				
					String name = city.split("\\(")[0].trim();
					
					String cod = city.split("\\(")[1].split("\\)")[0].trim();
					
					LocationCityContextualAttribute l = new LocationCityContextualAttribute(name,new Long(cod));
					LocationCityContextualAttribute.cities.add(l);
				}
				
				line = reader.readLine();
					
				
			}
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public long getCode() {
		return this.code;
	}
	
	public static ArrayList<LocationCityContextualAttribute> values() {
		return cities;
	}

	public static LocationCityContextualAttribute getInstanceByCode(long code) {

		for (LocationCityContextualAttribute d : LocationCityContextualAttribute
				.values()) {
			if (d.getCode() == code) {
				return d;
			}
		}

		return null;
	}
	
	private static LocationCityContextualAttribute getInstanceByName(String name) {

		for (LocationCityContextualAttribute d : LocationCityContextualAttribute
				.values()) {
			if (d.name().equalsIgnoreCase(name)) {
				return d;
			}
		}

		return null;
	}

	public static LocationCityContextualAttribute getEnum(String name) {
		
		try{
			
			String state = name.replaceAll("\\s", "_").replaceAll("\\-", "_").replaceAll("\\'", "_").toUpperCase();
			return getInstanceByName(state);
		}catch(Exception e){
		
			if (name.equalsIgnoreCase("Charleville-Mezières")) {
				return getInstanceByName("CHARLEVILLE_MEZI�RES");
			}else if (name.equalsIgnoreCase("Hères")) {
				return getInstanceByName("H�RES");
			}else if (name.equalsIgnoreCase("Rīga")) {
				return getInstanceByName("RIGA");
			}else if (name.equalsIgnoreCase("Varaždin")) {
				return getInstanceByName("VARA�DIN");
			}else if (name.equalsIgnoreCase("Jaroměř")) {
				return getInstanceByName("JAROMER");
			}else if (name.equalsIgnoreCase("�?frica")) {
				return getInstanceByName("�FRICA");
			}else if (name.equalsIgnoreCase("Parañaque")) {
				return getInstanceByName("PARA�AQUE");
			}else if (name.equalsIgnoreCase("Vršac")) {
				return getInstanceByName("VR�AC");
			}else if (name.equalsIgnoreCase("Västerås")) {
				return getInstanceByName("V�STER�S");
			}else if (name.equalsIgnoreCase("Tønsberg")) {
				return getInstanceByName("T�NSBERG");
			}else if (name.equalsIgnoreCase("Dasmariñas")) {
				return getInstanceByName("DASMARI�AS");
			}else if (name.equalsIgnoreCase("Zürich")) {
				return getInstanceByName("Z�RICH");
			}else if (name.equalsIgnoreCase("Les Haudères")) {
				return getInstanceByName("LES_HAUD�RES");
			}else if (name.equalsIgnoreCase("Lempäälä")) {
				return getInstanceByName("LEMP��L�");
			}else if (name.equalsIgnoreCase("Maršov")) {
				return getInstanceByName("MAR�OV");
			}else if (name.equalsIgnoreCase("MAYAGüEZ")) {
				return getInstanceByName("MAYAGAEZ");
			}else if (name.equalsIgnoreCase("LEóN")) {
				return getInstanceByName("LEON");
			}else if (name.equalsIgnoreCase("SUłKOWICE")) {
				return getInstanceByName("SUAKOWICE");
			}else if (name.equalsIgnoreCase("ESPAñOLA")) {
				return getInstanceByName("ESPANOLA");
			}else if (name.equalsIgnoreCase("PEñALOLéN")) {
				return getInstanceByName("PEALOLON");
			}else if (name.equalsIgnoreCase("BANSKá BYSTRICA")) {
				return getInstanceByName("BANSKA_BYSTRICA");
			}else if (name.equalsIgnoreCase("BESANçON")) {
				return getInstanceByName("BESANAON");
			}else if (name.equalsIgnoreCase("TIMIșOARA")) {
				return getInstanceByName("TIMIEOARA");
			}else{
				if(name.length()>1){
					System.err.println(name + " unknown city");
				}
				return getInstanceByName("UNKNOWN");
			}
			/*else if (name.equalsIgnoreCase("south carolina")) {
				return SOUTH_CAROLINA;
			}else if (name.equalsIgnoreCase("new mexico")) {
				return NEW_MEXICO;
			}else if (name.equalsIgnoreCase("new hampshire")) {
				return NEWHAMPSHIRE;
			}else if (name.equalsIgnoreCase("district of columbia")) {
				return DISTRICT_OF_COLUMBIA;
			}else if (name.equalsIgnoreCase("rhode island")) {
				return RHODE_ISLAND;
			}else if (name.equalsIgnoreCase("west virginia")) {
				return WEST_VIRGINIA;
			}else if (name.equalsIgnoreCase("north dakota")) {
				return NORTH_DAKOTA;
			}else if (name.equalsIgnoreCase("south dakota")) {
				return SOUTH_DAKOTA;
			}else{
				//System.out.println(name + " category unknown");
				return OTHER;
			}*/
		}
	}

	@Override
	public List<AbstractContextualAttribute> valuesForTest() {

		HashSet<Long> aux = new HashSet<Long>();

		List<AbstractContextualAttribute> valuesForTest = new ArrayList<AbstractContextualAttribute>();
		for (LocationCityContextualAttribute attr : LocationCityContextualAttribute
				.values()) {
			if (!attr.equals(LocationCityContextualAttribute.getEnum("UNKNOWN"))//FIXME: ZUTPHEN s� para books e movies (economia de testes)
					&& !aux.contains(attr.getCode()) /*&& attr.getCode() <= LocationCityContextualAttribute.ZUTPHEN.code*/) {
				valuesForTest.add(attr);
				aux.add(attr.getCode());
			}
		}
		return valuesForTest;
	}

	
	/*public static void main(String[] args) {
		LocationCityContextualAttribute l = new LocationCityContextualAttribute();
		for(LocationCityContextualAttribute l2 : LocationCityContextualAttribute.values()){
			System.out.println(l2.name());
		}
	}*/
	
}
