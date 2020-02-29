/**
 *******************************************************************************
 *
 * NumericalChameleon 3.0.0 - more than an unit converter - a NumericalChameleon
 * Copyright (c) 2001-2020 Dipl.-Inf. (FH) Johann Nepomuk Loefflmann, All Rights
 * Reserved, <http://www.numericalchameleon.net>.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *******************************************************************************
 *
 * THIS FILE HAS BEEN GENERATED AUTOMACALLY BY XML2JAVA WHICH IS
 * COPYRIGHT BY JOHANN LOEFFLMANN
 *
 *****************************************************************************/

package net.numericalchameleon.categories;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import jonelo.sugar.datetime.ExtendedGregorianCalendar;
import net.numericalchameleon.util.calendar.ChineseCalendarCalculations;
import net.numericalchameleon.data.Unit;


public class CategoryHolidays extends CategoryObject {

    // This class supports 1040 units

    private final static int
      // source
      cYEAR = 0;

      /* CONSTANTS */

    public static String
      NOT_DEFINED = "not defined",
      UNKNOWN = "unknown, not implemented",
      YEAR = "Year",
      INVALID = "Source is invalid",
      YES = "yes",
      NO = "no";

    private ArrayList<Unit> sourceUnits, targetUnits;
	private Unit defaultSourceUnit, defaultTargetUnit;
    private DateFormat df;

    public CategoryHolidays(CategoryInterface categoryInterface) {
	    rb = categoryInterface.getResourceBundle();
        df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        // source
        sourceUnits = new ArrayList<>();
        sourceUnits.add(new Unit(cYEAR, YEAR, "int"));
		
		defaultSourceUnit = sourceUnits.get(0);

        // target
        // international
        targetUnits = new ArrayList<>();
        
// int
targetUnits.add(new Unit(0, "World Leprosy Day", "int"));
targetUnits.add(new Unit(1, "World Braille Day", "int"));
targetUnits.add(new Unit(2, "National Hugging Day", "int"));
targetUnits.add(new Unit(3, "Change Your Password Day", "int"));
targetUnits.add(new Unit(4, "World Cancer Day", "int"));
targetUnits.add(new Unit(5, "Safer Internet Day", "int"));
targetUnits.add(new Unit(6, "International Ear Care Day", "int"));
targetUnits.add(new Unit(7, "World Hearing Day", "int"));
targetUnits.add(new Unit(8, "World Meteorological day", "int"));
targetUnits.add(new Unit(9, "Pi Day", "int"));
targetUnits.add(new Unit(10, "Earth Day", "int"));
targetUnits.add(new Unit(11, "World Password Day", "int"));
targetUnits.add(new Unit(12, "Towel Day", "int"));
targetUnits.add(new Unit(13, "International Kissing Day", "int"));
targetUnits.add(new Unit(14, "World Chocolate Day", "int"));
targetUnits.add(new Unit(15, "International Beer Day", "int"));
targetUnits.add(new Unit(16, "World Animal Day", "int"));
targetUnits.add(new Unit(17, "International Tea Day", "int"));

// uno
targetUnits.add(new Unit(1000, "International Holocaust Remembrance Day", "uno"));
targetUnits.add(new Unit(1001, "World Cancer Day", "uno"));
targetUnits.add(new Unit(1002, "International Women's Day", "uno"));
targetUnits.add(new Unit(1003, "World Water Day", "uno"));
targetUnits.add(new Unit(1004, "World Health Day", "uno"));
targetUnits.add(new Unit(1005, "World Book and Copyright Day", "uno"));
targetUnits.add(new Unit(1006, "World Press Freedom Day", "uno"));
targetUnits.add(new Unit(1007, "International Day for Biological Diversity", "uno"));
targetUnits.add(new Unit(1008, "World Environment Day", "uno"));
targetUnits.add(new Unit(1009, "International Day of Cooperatives", "uno"));
targetUnits.add(new Unit(1010, "World Statistics Day", "uno"));
targetUnits.add(new Unit(1011, "United Nations Day", "uno"));
targetUnits.add(new Unit(1012, "Universal Children's Day", "uno"));
targetUnits.add(new Unit(1013, "World Toilet Day", "uno"));
targetUnits.add(new Unit(1014, "Africa Industrialization Day", "uno"));
targetUnits.add(new Unit(1015, "World AIDS Day", "uno"));
targetUnits.add(new Unit(1016, "Human Rights Day", "uno"));

// us
targetUnits.add(new Unit(2000, "New Year's Day [federal holiday]", "us"));
targetUnits.add(new Unit(2001, "Three Kings Day", "us"));
targetUnits.add(new Unit(2002, "Martin Luther King, Jr's Birthday [actual birthday]", "us", false));
targetUnits.add(new Unit(2003, "Martin Luther King, Jr. Day [observed, national]", "us"));
targetUnits.add(new Unit(2004, "Inauguration Day", "us"));
targetUnits.add(new Unit(2005, "Groundhog Day [cultural]", "us"));
targetUnits.add(new Unit(2006, "Mardi Gras (Fat Tuesday) [cultural, christian; New Orleans, Louisiana]", "us"));
targetUnits.add(new Unit(2007, "Ash Wednesday", "us"));
targetUnits.add(new Unit(2008, "Laetare Sunday (Mothering Sunday, Mid-Lent Sunday, Rose Sunday)", "us"));
targetUnits.add(new Unit(2009, "Lincoln's Birthday [local]", "us", false));
targetUnits.add(new Unit(2010, "Valentine's Day", "us"));
targetUnits.add(new Unit(2011, "Washington's Birthday (President's Day) [federal holiday]", "us"));
targetUnits.add(new Unit(2012, "National Teacher Day", "us"));
targetUnits.add(new Unit(2013, "Saint Patrick's Day [Christian, Festive]", "us"));
targetUnits.add(new Unit(2014, "Saint Joseph's Day [Christian, Catholic]", "us"));
targetUnits.add(new Unit(2015, "April Fools' Day", "us"));
targetUnits.add(new Unit(2016, "Daylight Saving Time Begins (DST), +1h", "us"));
targetUnits.add(new Unit(2017, "Tax Day", "us"));
targetUnits.add(new Unit(2018, "Earth Day", "us"));
targetUnits.add(new Unit(2019, "National Secretaries Day", "us", false));
targetUnits.add(new Unit(2020, "Professional Secretaries Day", "us", false));
targetUnits.add(new Unit(2021, "Administrative Professionals Day", "us"));
targetUnits.add(new Unit(2022, "Palm Sunday", "us"));
targetUnits.add(new Unit(2023, "Good Friday", "us"));
targetUnits.add(new Unit(2024, "Holy Saturday (Easter Even)", "us"));
targetUnits.add(new Unit(2025, "Easter Day", "us"));
targetUnits.add(new Unit(2026, "Arbor Day [cultural]", "us"));
targetUnits.add(new Unit(2027, "Cinco de Mayo [Mexican American]", "us"));
targetUnits.add(new Unit(2028, "Mother's Day", "us"));
targetUnits.add(new Unit(2029, "Memorial Day [federal holiday]", "us"));
targetUnits.add(new Unit(2030, "Flag Day", "us"));
targetUnits.add(new Unit(2031, "Father's Day", "us"));
targetUnits.add(new Unit(2032, "Independence Day (The Fourth of July) [federal holiday]", "us"));
targetUnits.add(new Unit(2033, "Independence Day (observed) [federal holiday]", "us"));
targetUnits.add(new Unit(2034, "Labor Day [federal holiday]", "us"));
targetUnits.add(new Unit(2035, "Patriot Day", "us"));
targetUnits.add(new Unit(2036, "Patriot's Day [Massachusetts and state of Maine]", "us"));
targetUnits.add(new Unit(2037, "Grandparents Day [national]", "us"));
targetUnits.add(new Unit(2038, "Columbus Day [federal holiday]", "us"));
targetUnits.add(new Unit(2039, "Boss' Day (National Boss Day, Bosses Day)", "us"));
targetUnits.add(new Unit(2040, "Daylight Saving Time Ends (DST), -1h", "us"));
targetUnits.add(new Unit(2041, "Halloween", "us"));
targetUnits.add(new Unit(2042, "All Saint's Day [Western Churches]", "us"));
targetUnits.add(new Unit(2043, "All Soul's Day", "us"));
targetUnits.add(new Unit(2044, "Election Day", "us"));
targetUnits.add(new Unit(2045, "Election of President and Vice President of the United States", "us"));
targetUnits.add(new Unit(2046, "Midterm elections", "us"));
targetUnits.add(new Unit(2047, "Armistice's Day [federal holiday]", "us", false));
targetUnits.add(new Unit(2048, "Veterans Day [federal holiday]", "us"));
targetUnits.add(new Unit(2049, "Thanksgiving [federal holiday]", "us"));
targetUnits.add(new Unit(2050, "Black Friday (Friday after Thanksgiving) [non federal holiday]", "us"));
targetUnits.add(new Unit(2051, "Christmas Eve", "us"));
targetUnits.add(new Unit(2052, "Christmas Day [federal holiday]", "us"));
targetUnits.add(new Unit(2053, "Kwanzaa (7 days) [African American]", "us"));
targetUnits.add(new Unit(2054, "New Year's Eve", "us"));
targetUnits.add(new Unit(2055, "First Day of Spring", "us"));
targetUnits.add(new Unit(2056, "First Day of Summer", "us"));
targetUnits.add(new Unit(2057, "First Day of Autumn", "us"));
targetUnits.add(new Unit(2058, "First Day of Winter", "us"));

// ca
targetUnits.add(new Unit(3000, "New Year's Day / Le jour de l'An [nationwide statutory holiday]", "ca"));
targetUnits.add(new Unit(3001, "Valentine's Day / Saint-Valentin", "ca"));
targetUnits.add(new Unit(3002, "National Flag of Canada Day", "ca"));
targetUnits.add(new Unit(3003, "Family Day / F\u00eate de la famille [common statutory holiday; Alberta, Ontario, Saskatchewan]", "ca"));
targetUnits.add(new Unit(3004, "Louis Riel Day [public holiday; Canadian province of Manitoba]", "ca"));
targetUnits.add(new Unit(3005, "Caribana", "ca"));
targetUnits.add(new Unit(3006, "Shrove Monday (Rose Monday)", "ca"));
targetUnits.add(new Unit(3007, "Strove Thuesday (Pancake Day, Pancake Tuesday)", "ca"));
targetUnits.add(new Unit(3008, "Ash Wednesday", "ca"));
targetUnits.add(new Unit(3009, "Daylight Saving Time starts (change time 1 h forward)", "ca"));
targetUnits.add(new Unit(3010, "Good Friday / Le vendredi saint [nationwide statutory holiday]", "ca"));
targetUnits.add(new Unit(3011, "Holy Saturday", "ca"));
targetUnits.add(new Unit(3012, "Easter Sunday / P\u00e2ques", "ca"));
targetUnits.add(new Unit(3013, "Easter Monday / Lundi de P\u00e2ques [statutory holiday]", "ca"));
targetUnits.add(new Unit(3014, "Mother's Day / F\u00eate des M\u00e8res", "ca"));
targetUnits.add(new Unit(3015, "Victoria Day / La f\u00eate de la Reine [statutory holiday]", "ca"));
targetUnits.add(new Unit(3016, "Canada Day / La f\u00eate du Canada [nationwide statutory holiday]", "ca"));
targetUnits.add(new Unit(3017, "August Civic Holiday / Premier lundi d'ao\u00fbt [local]", "ca"));
targetUnits.add(new Unit(3018, "Discovery Day [statutory holiday; Yukon]", "ca"));
targetUnits.add(new Unit(3019, "Discovery Day [Newfoundland and Labrador]", "ca"));
targetUnits.add(new Unit(3020, "Nunavut Day [Nunavut]", "ca"));
targetUnits.add(new Unit(3021, "National Aboriginal Day [Northwest Territories]", "ca"));
targetUnits.add(new Unit(3022, "Memorial Day [Newfoundland and Labrador]", "ca"));
targetUnits.add(new Unit(3023, "Orangemen's Day  [Newfoundland and Labrador]", "ca"));
targetUnits.add(new Unit(3024, "St. Patrick's Day [public holiday; Newfoundland and Labrador]", "ca"));
targetUnits.add(new Unit(3025, "St. George's Day [Newfoundland and Labrador]", "ca"));
targetUnits.add(new Unit(3026, "Labour Day / La f\u00eate du travail [nationwide statutory holiday]", "ca"));
targetUnits.add(new Unit(3027, "Thanksgiving / L'Action de gr\u00e2ce [statutory holiday, cultural]", "ca"));
targetUnits.add(new Unit(3028, "Halloween", "ca"));
targetUnits.add(new Unit(3029, "Daylight Saving Time ends (change time 1 h back)", "ca"));
targetUnits.add(new Unit(3030, "Remembrance Day / Le jour du Souvenir [all except NB, NS, ON, QC]", "ca"));
targetUnits.add(new Unit(3031, "First Sunday Advent", "ca"));
targetUnits.add(new Unit(3032, "Second Sunday Advent", "ca"));
targetUnits.add(new Unit(3033, "Third Sunday Advent (Gaudete)", "ca"));
targetUnits.add(new Unit(3034, "Fourth Sunday Advent", "ca"));
targetUnits.add(new Unit(3035, "Christmas Eve / R\u00e9veillon de No\u00ebl", "ca"));
targetUnits.add(new Unit(3036, "Christmas Day / No\u00ebl [nationwide statutory holiday]", "ca"));
targetUnits.add(new Unit(3037, "Boxing Day / Le lendemain de No\u00ebl [statutory holiday]", "ca"));

// de
targetUnits.add(new Unit(4000, "Neujahrstag [New Year's Day; public holiday]", "de"));
targetUnits.add(new Unit(4001, "Hl. Drei K\u00f6nige (Dreik\u00f6nigsfest) [Biblical Magi; public holiday; in BW,BY,ST]", "de"));
targetUnits.add(new Unit(4002, "Internationaler Frauentag [International Women's Day; public holiday; in BE]", "de"));
targetUnits.add(new Unit(4003, "Erscheinung des Herrn (Epiphanie) [Epiphany; protestant]", "de"));
targetUnits.add(new Unit(4004, "Taufe des Herrn", "de"));
targetUnits.add(new Unit(4005, "Tag des Gedenkens an die Opfer des Nationalsozialismus [The International Holocaust Remembrance Day; national]", "de"));
targetUnits.add(new Unit(4006, "Darstellung des Herrn (Mari\u00e4 Lichtmess)", "de"));
targetUnits.add(new Unit(4007, "Valentinstag [Valentine's Day]", "de"));
targetUnits.add(new Unit(4008, "Rosenmontag [Rose Monday]", "de"));
targetUnits.add(new Unit(4009, "Fastnacht (Faschingsdienstag) [Carnival]", "de"));
targetUnits.add(new Unit(4010, "Aschermittwoch [Ash Wednesday]", "de"));
targetUnits.add(new Unit(4011, "1. Fastensonntag (Invocavit)", "de"));
targetUnits.add(new Unit(4012, "2. Fastensonntag (Reminiscere)", "de"));
targetUnits.add(new Unit(4013, "3. Fastensonntag (Oculi)", "de"));
targetUnits.add(new Unit(4014, "4. Fastensonntag (L\u00e4tare, Rosensonntag, Brotsonntag, Todsonntag, Stabaus-Sonntag)", "de"));
targetUnits.add(new Unit(4015, "5. Fastensonntag (Judica, Passionssonntag, Schwarzer Sonntag)", "de"));
targetUnits.add(new Unit(4016, "Beginn der Sommerzeit (Uhren 1 h vorstellen) [Begin Daylight Saving Time]", "de"));
targetUnits.add(new Unit(4017, "Dreifacher Hexensabbat, gro\u00dfer Verfallstag (1. Quartal) [Triple witching hour (1st quarter); stock market]", "de"));
targetUnits.add(new Unit(4018, "Dreifacher Hexensabbat, gro\u00dfer Verfallstag (2. Quartal) [Triple witching hour (2nd quarter); stock market]", "de"));
targetUnits.add(new Unit(4019, "Tag des offenen Denkmals [European Heritage Days; cultural]", "de"));
targetUnits.add(new Unit(4020, "Weltkindertag [International Children's Day; public holiday; TH]", "de"));
targetUnits.add(new Unit(4021, "Dreifacher Hexensabbat, gro\u00dfer Verfallstag (3. Quartal) [Triple witching hour (3rd quarter); stock market]", "de"));
targetUnits.add(new Unit(4022, "Dreifacher Hexensabbat, gro\u00dfer Verfallstag (4. Quartal) [Triple witching hour (4th quarter); stock market]", "de"));
targetUnits.add(new Unit(4023, "Erster April [April Fool's Day]", "de"));
targetUnits.add(new Unit(4024, "Walpurgisnacht [Walpurgis Night]", "de"));
targetUnits.add(new Unit(4025, "Palmsonntag (Palmarum) [Palm Sunday]", "de"));
targetUnits.add(new Unit(4026, "Krummer Mittwoch [Holy Wednesday]", "de", false));
targetUnits.add(new Unit(4027, "Gr\u00fcndonnerstag [Maundy Thursday]", "de"));
targetUnits.add(new Unit(4028, "Karfreitag [Good Friday; public holiday]", "de"));
targetUnits.add(new Unit(4029, "Karsamstag [Holy Saturday]", "de"));
targetUnits.add(new Unit(4030, "Ostersonntag (Ostern) [Easter Sunday]", "de"));
targetUnits.add(new Unit(4031, "Ostermontag (Emmaustag) [Easter Monday; public holiday]", "de"));
targetUnits.add(new Unit(4032, "Wei\u00dfer Sonntag (Barmherzigkeitssonntag) [Second Sunday of Easter; catholic]", "de"));
targetUnits.add(new Unit(4033, "Quasimodogeniti [First Sunday after Easter; protestant]", "de"));
targetUnits.add(new Unit(4034, "Maifeiertag (Tag der Arbeit) [Labour Day; public holiday]", "de"));
targetUnits.add(new Unit(4035, "Muttertag [Mother's Day]", "de"));
targetUnits.add(new Unit(4036, "Kantate [Kantate; protestant]", "de"));
targetUnits.add(new Unit(4037, "Exaudi [Exaudi; protestant]", "de"));
targetUnits.add(new Unit(4038, "Christi Himmelfahrt [Ascension Day; public holiday]", "de"));
targetUnits.add(new Unit(4039, "Pfingstsonntag (Pfingsten) [Pentecost]", "de"));
targetUnits.add(new Unit(4040, "Pfingstmontag [Whit Monday; public holiday]", "de"));
targetUnits.add(new Unit(4041, "Eisheilige: Mamertus", "de"));
targetUnits.add(new Unit(4042, "Eisheilige: Pankratius", "de"));
targetUnits.add(new Unit(4043, "Eisheilige: Servatius", "de"));
targetUnits.add(new Unit(4044, "Eisheilige: Bonifatius", "de"));
targetUnits.add(new Unit(4045, "Eisheilige: Sophie", "de"));
targetUnits.add(new Unit(4046, "Nationaler Gedenktag", "de", false));
targetUnits.add(new Unit(4047, "Fronleichnam [Corpus Christi; public holiday; BW,BY,HE,NW,RP,SL,SN partly,TH partly]", "de"));
targetUnits.add(new Unit(4048, "Friedensfest [Peace Festival; public holiday; only in Augsburg]", "de"));
targetUnits.add(new Unit(4049, "Mari\u00e4 Himmelfahrt [Assumption Day; catholic; SL,BY]", "de"));
targetUnits.add(new Unit(4050, "Oktoberfest (Er\u00f6ffnung) [Oktoberfest (Start); Festival; Munich]", "de"));
targetUnits.add(new Unit(4051, "Oktoberfest (Ende) [Oktoberfest (End); Festival; Munich]", "de"));
targetUnits.add(new Unit(4052, "Tag der deutschen Einheit [German Unity Day; public holiday]", "de"));
targetUnits.add(new Unit(4053, "Erntedankfest [Harvest festival]", "de"));
targetUnits.add(new Unit(4054, "Ende der Sommerzeit (Uhren 1 h zur\u00fcckstellen) [End Daylight Saving Time]", "de"));
targetUnits.add(new Unit(4055, "Rosenkranzfest [Our Lady of the Rosary; catholic]", "de"));
targetUnits.add(new Unit(4056, "Kirchweih", "de"));
targetUnits.add(new Unit(4057, "Weltspartag (gefeiert) [World Savings Day (observed)]", "de"));
targetUnits.add(new Unit(4058, "Weltspartag (offiziell) [World Savings Day (official)]", "de"));
targetUnits.add(new Unit(4059, "Reformationstag [Reformation Day; public holiday, protestant; BB, MV, SN, ST, TH]", "de"));
targetUnits.add(new Unit(4060, "Reformationstag [Reformation Day; public holiday, protestant; SH, HH, NS, BR]", "de"));
targetUnits.add(new Unit(4061, "Reformationstag [Reformation Day; public holiday]", "de"));
targetUnits.add(new Unit(4062, "Allerheiligen [All Saint's Day; public holiday; BW, BY, NW, RP, SL]", "de"));
targetUnits.add(new Unit(4063, "Allerseelen [All Soul's Day; catholic]", "de"));
targetUnits.add(new Unit(4064, "Martinstag [St. Martin's Day]", "de"));
targetUnits.add(new Unit(4065, "Volkstrauertag [National Day of Mourning]", "de"));
targetUnits.add(new Unit(4066, "Bu\u00df- und Bettag [Day of Repentance and Prayer; public holiday; SA]", "de"));
targetUnits.add(new Unit(4067, "Bu\u00df- und Bettag [Day of Repentance and Prayer; public holiday]", "de"));
targetUnits.add(new Unit(4068, "Totensonntag (Ewigkeitssonntag) [Sunday of the Dead (Eternity Sunday); protestant]", "de"));
targetUnits.add(new Unit(4069, "Nikolaus [Saint Nicholas Day]", "de"));
targetUnits.add(new Unit(4070, "Mari\u00e4 Empf\u00e4ngnis [Immaculate Conception]", "de"));
targetUnits.add(new Unit(4071, "Christk\u00f6nigsfest [Feast of Christ the King; catholic]", "de"));
targetUnits.add(new Unit(4072, "1. Advent", "de"));
targetUnits.add(new Unit(4073, "2. Advent", "de"));
targetUnits.add(new Unit(4074, "3. Advent (Gaudete)", "de"));
targetUnits.add(new Unit(4075, "4. Advent", "de"));
targetUnits.add(new Unit(4076, "Heiligabend [Christmas Eve]", "de"));
targetUnits.add(new Unit(4077, "1. Weihnachtsfeiertag (Weihnachten) [Christmas Day; public holiday]", "de"));
targetUnits.add(new Unit(4078, "2. Weihnachtsfeiertag (Weihnachten) [Boxing Day; public holiday]", "de"));
targetUnits.add(new Unit(4079, "Silvester [New Year's Eve]", "de"));
targetUnits.add(new Unit(4080, "Fr\u00fchlingsanfang [March Equinox]", "de"));
targetUnits.add(new Unit(4081, "Sommeranfang (Sommersonnenwende) [June Solstice]", "de"));
targetUnits.add(new Unit(4082, "Herbstanfang [September Equinox]", "de"));
targetUnits.add(new Unit(4083, "Winteranfang (Wintersonnenwende) [December Solstice]", "de"));

// at
targetUnits.add(new Unit(5000, "Neujahrstag", "at"));
targetUnits.add(new Unit(5001, "Hl. Drei K\u00f6nige [Three Kings Day]", "at"));
targetUnits.add(new Unit(5002, "Josef", "at"));
targetUnits.add(new Unit(5003, "Beginn der Sommerzeit (Uhren 1 h vorstellen) [Start Daylight Saving Time]", "at"));
targetUnits.add(new Unit(5004, "Karfreitag", "at"));
targetUnits.add(new Unit(5005, "Ostersonntag (Ostern)", "at"));
targetUnits.add(new Unit(5006, "Ostermontag", "at"));
targetUnits.add(new Unit(5007, "Staatsfeiertag (Tag der Arbeit) [Labour Day]", "at"));
targetUnits.add(new Unit(5008, "Florian", "at"));
targetUnits.add(new Unit(5009, "Muttertag [Mother's Day]", "at"));
targetUnits.add(new Unit(5010, "Vatertag [Father's Day]", "at"));
targetUnits.add(new Unit(5011, "Christi Himmelfahrt", "at"));
targetUnits.add(new Unit(5012, "Pfingstsonntag (Pfingsten)", "at"));
targetUnits.add(new Unit(5013, "Pfingstmontag", "at"));
targetUnits.add(new Unit(5014, "Fronleichnam", "at"));
targetUnits.add(new Unit(5015, "Mari\u00e4 Himmelfahrt", "at"));
targetUnits.add(new Unit(5016, "Rupert", "at"));
targetUnits.add(new Unit(5017, "Tag des Denkmals [European Heritage Days; cultural]", "at"));
targetUnits.add(new Unit(5018, "Tag der Volksabstimmung", "at"));
targetUnits.add(new Unit(5019, "Nationalfeiertag", "at"));
targetUnits.add(new Unit(5020, "Ende der Sommerzeit (Uhren 1 h zur\u00fcckstellen) [End Daylight Saving Time]", "at"));
targetUnits.add(new Unit(5021, "Weltspartag (gefeiert)", "at"));
targetUnits.add(new Unit(5022, "Weltspartag (offiziell)", "at"));
targetUnits.add(new Unit(5023, "Allerheiligen", "at"));
targetUnits.add(new Unit(5024, "Martin", "at"));
targetUnits.add(new Unit(5025, "Leopold", "at"));
targetUnits.add(new Unit(5026, "Mari\u00e4 Empf\u00e4ngnis [Immaculate Conception]", "at"));
targetUnits.add(new Unit(5027, "Heiliger Abend", "at"));
targetUnits.add(new Unit(5028, "Christtag", "at"));
targetUnits.add(new Unit(5029, "Stefanitag", "at"));
targetUnits.add(new Unit(5030, "Silvester", "at"));
targetUnits.add(new Unit(5031, "Fr\u00fchlingsanfang [Equinox March]", "at"));
targetUnits.add(new Unit(5032, "Sommeranfang (Sommersonnenwende) [Solstice June]", "at"));
targetUnits.add(new Unit(5033, "Herbstanfang [Equinox September]", "at"));
targetUnits.add(new Unit(5034, "Winteranfang (Wintersonnenwende) [Solstice December]", "at"));

// ch
targetUnits.add(new Unit(6000, "Neujahr / Nouvel an / Capodanno / Bumaun [New Year's Day; public]", "ch"));
targetUnits.add(new Unit(6001, "Berchtoldstag (B\u00e4chtelistag) / Saint Berchtold / San Basilio / Fir\u00e0 da Bumaun [Saint Berchtold; public; AG (partly), BE, FR, GL, JU, LU, NE, OW, SH, SO, TG, VD, ZG, ZH]", "ch"));
targetUnits.add(new Unit(6002, "Heilige drei K\u00f6nige / Epiphanie / Epifania [Biblical Magi; public; GR (partly), SZ, TI, UR, LU]", "ch"));
targetUnits.add(new Unit(6003, "Valentinstag / Saint-Valentin / San Valentino [Valentine's Day]", "ch"));
targetUnits.add(new Unit(6004, "Josefstag / Saint Joseph / San Giuseppe [Saint Joseph's Day; public; cath. regions: GR (partly), LU (partly), NW, SO (partly), SZ, TI, UR, VS]", "ch"));
targetUnits.add(new Unit(6005, "[Fat Thursday; LU, NW, UR]", "ch"));
targetUnits.add(new Unit(6006, "[Carnival; SZ, GL]", "ch"));
targetUnits.add(new Unit(6007, "[Carnival; SZ]", "ch"));
targetUnits.add(new Unit(6008, "[Carnival in Basel (begin); only BS]", "ch"));
targetUnits.add(new Unit(6009, "[Carnival in Basel (end); only BS]", "ch"));
targetUnits.add(new Unit(6010, "[Republic's day; only NE]", "ch"));
targetUnits.add(new Unit(6011, "[N\u00e4felser Fahrt; only GL]", "ch"));
targetUnits.add(new Unit(6012, "Muttertag / F\u00eate des M\u00e8res [Mother's Day]", "ch"));
targetUnits.add(new Unit(6013, "V\u00e4tertag [Father's Day]", "ch"));
targetUnits.add(new Unit(6014, "Vatertag, Josefstag / F\u00eate des P\u00e8res [Father's Day]", "ch"));
targetUnits.add(new Unit(6015, "[Independence day; only JU]", "ch"));
targetUnits.add(new Unit(6016, "[Je\u00fbne genevois; GE]", "ch"));
targetUnits.add(new Unit(6017, "Europ\u00e4ischer Tag des Denkmals [European Heritage Days; cultural]", "ch"));
targetUnits.add(new Unit(6018, "[Knabenschiessen (begin); only city (and agglomeration) of Zurich]", "ch"));
targetUnits.add(new Unit(6019, "[Knabenschiessen (end); only city (and agglomeration) of Zurich]", "ch"));
targetUnits.add(new Unit(6020, "Beginn der Sommerzeit (Uhren 1 h vorstellen) [Daylight Saving Time (begin)]", "ch"));
targetUnits.add(new Unit(6021, "Karfreitag / Venderdi sontg / Venerd\u00ec santo [Good Friday; public; all of Switzerland except TI, VS]", "ch"));
targetUnits.add(new Unit(6022, "Ostersonntag (Ostern)", "ch"));
targetUnits.add(new Unit(6023, "Ostermontag / Lundi de P\u00e2ques / Luned\u00ec di Pasqua / Glindesdi da Pasca [Easter Monday; public; all of Switzerland, except VS]", "ch"));
targetUnits.add(new Unit(6024, "Tag der Arbeit / F\u00eate du travail / Festa del lavoro / Di da la lavur [Labour Day; public; BL, BS, FR (partly), JU, LU (partly), SH, SO (partly), TG, TI, ZH]", "ch"));
targetUnits.add(new Unit(6025, "Auffahrt / Ascension / Ascensione / Anzainzas [Ascension Day; public; all of Switzerland]", "ch"));
targetUnits.add(new Unit(6026, "Pfingstsonntag (Pfingsten) [Pentecost]", "ch"));
targetUnits.add(new Unit(6027, "Pfingstmontag / Lundi de Pentec\u00f4te / Luned\u00ec di Pentecoste / Glindesdi da Tschuncaisma [Whit Monday; public; all of Switzerland, except VS]", "ch"));
targetUnits.add(new Unit(6028, "Fronleichnam / F\u00eate-Dieu / Corpus Domini / Sontgilcrest [Corpus Christi; public; Catholic regions: AG (partly), AI, FR (partly), GR (partly), JU, LU, NW, OW, SO, SZ, TI, UR, VS, ZG]", "ch"));
targetUnits.add(new Unit(6029, "Peter und Paul / Sts Pierre et Paul / SS. Pietro e Paolo [St. Peter and St. Paul; public; GR (partly), TI]", "ch"));
targetUnits.add(new Unit(6030, "Schweizer Nationalfeiertag / F\u00eate nationale Suisse / Festa nazionale della Svizzera / Festa naziunala [Swiss National Day; public; all of Switzerland]", "ch"));
targetUnits.add(new Unit(6031, "Mari\u00e4 Himmelfahrt / Assomption / Assunzione / Assumziun [Assumption of Mary; public; Catholic regions: AG (partly), AI, FR (partly), GR (partly), JU, LU, NW, OW, SO, SZ, TI, UR, VS, ZG]", "ch"));
targetUnits.add(new Unit(6032, "Eidgen\u00f6ssischer Dank-, Buss- und Bettag / Je\u00fbne f\u00e9d\u00e9ral / Digiuno federale / Festa da la rogaziun federala [Swiss federal fast; public; all of Switzerland, except GE]", "ch"));
targetUnits.add(new Unit(6033, "Ende der Sommerzeit (Uhren 1 h zur\u00fcckstellen) [Daylight Saving Time (end)]", "ch"));
targetUnits.add(new Unit(6034, "Bettagsmontag / Lundi du Je\u00fbne f\u00e9d\u00e9ral / Luned\u00ec del digiuno federale / Gliendischdis da la rogaziun federala [Swiss federal fast Monday; public; VD, NE (partly), BE (partly)]", "ch"));
targetUnits.add(new Unit(6035, "[Bruderklausenfest; only OW]", "ch"));
targetUnits.add(new Unit(6036, "Allerheiligen / Toussaint / Ognissanti / Numnasontga [All Saints; public; Catholic regions: AG (partly), AI, FR (partly), GL, GR (partly), JU, LU, NW, OW, SG, SO, SZ, TI, UR, VS, ZG]", "ch"));
targetUnits.add(new Unit(6037, "1. Advent", "ch"));
targetUnits.add(new Unit(6038, "2. Advent", "ch"));
targetUnits.add(new Unit(6039, "3. Advent", "ch"));
targetUnits.add(new Unit(6040, "4. Advent", "ch"));
targetUnits.add(new Unit(6041, "Mari\u00e4 Empf\u00e4ngnis / Immacul\u00e9e Conception / Immacolata Concezione / Immaculata concepziun [Immaculate Conception; public; Catholic regions: AG (partly), AI, FR (partly), GR (partly), LU, NW, OW, SO (partly), SZ, TI, UR, VS, ZG]", "ch"));
targetUnits.add(new Unit(6042, "Weihnachtstag / No\u00ebl / Natale / Di da Nadal [Christmas; public; all of Switzerland]", "ch"));
targetUnits.add(new Unit(6043, "Stephanstag / Saint Etienne / Santo Stefano / Son Steffan [St. Stephen's Day; public; all of Switzerland, except GE, JU, VD, VS, NE]", "ch"));
targetUnits.add(new Unit(6044, "Stephanstag / Saint Etienne / Santo Stefano / Son Steffan [St. Stephen's Day; public; only NE]", "ch"));
targetUnits.add(new Unit(6045, "[Restoration of the Republic; only GE]", "ch"));

// fr
targetUnits.add(new Unit(7000, "Jour de l'An [New Year's Day]", "fr"));
targetUnits.add(new Unit(7001, "Saint-Valentin [Valentine's Day]", "fr"));
targetUnits.add(new Unit(7002, "\u00c9quinoxe mars", "fr"));
targetUnits.add(new Unit(7003, "[Begin Daylight Saving Time]", "fr"));
targetUnits.add(new Unit(7004, "P\u00e2ques", "fr"));
targetUnits.add(new Unit(7005, "Lundi de P\u00e2ques", "fr"));
targetUnits.add(new Unit(7006, "F\u00eate du Travail [Labour Day; public holiday]", "fr"));
targetUnits.add(new Unit(7007, "Ascension [Ascension Day]", "fr"));
targetUnits.add(new Unit(7008, "Victoire 1945 [V-E Day]", "fr"));
targetUnits.add(new Unit(7009, "Pentec\u00f4te", "fr"));
targetUnits.add(new Unit(7010, "Lundi de Pentec\u00f4te [Whit Monday]", "fr"));
targetUnits.add(new Unit(7011, "F\u00eate des M\u00e8res [Mother's Day]", "fr"));
targetUnits.add(new Unit(7012, "Solstice juin", "fr"));
targetUnits.add(new Unit(7013, "F\u00eate Nationale [Bastille Day]", "fr"));
targetUnits.add(new Unit(7014, "Assomption [Assumption of Mary]", "fr"));
targetUnits.add(new Unit(7015, "\u00c9quinoxe sept", "fr"));
targetUnits.add(new Unit(7016, "[End Daylight Saving Time]", "fr"));
targetUnits.add(new Unit(7017, "Toussaint [All Saint's Day]", "fr"));
targetUnits.add(new Unit(7018, "Armistice 1918 [Veterans Day]", "fr"));
targetUnits.add(new Unit(7019, "Solstice d\u00e9c", "fr"));
targetUnits.add(new Unit(7020, "No\u00ebl [Christmas Day]", "fr"));
targetUnits.add(new Unit(7021, "La Saint-Sylvestre [New Year's Eve]", "fr"));

// es
targetUnits.add(new Unit(8000, "A\u00f1o Nuevo [New Year's Day; National holiday]", "es"));
targetUnits.add(new Unit(8001, "[Epiphany; CM, CE, AN, M, S, O, ML]", "es"));
targetUnits.add(new Unit(8002, "D\u00eda de Andaluc\u00eda [Day of Andaluc\u00eda; cultural, regional; Andaluc\u00eda (AN)]", "es"));
targetUnits.add(new Unit(8003, "D\u00eda de las Islas Baleares [Day of the Balearic Islands; cultural, regional; Islas Baleares (IB)]", "es"));
targetUnits.add(new Unit(8004, "San Jos\u00e9 [Father's Day; regional; Castile-La Mancha, Madrid, Murcia and Valencia]", "es"));
targetUnits.add(new Unit(8005, "Equinocio de marzo [Vernal equinox]", "es"));
targetUnits.add(new Unit(8006, "Jueves Santo [Holy Thursday; All regions except Canary Islands, Catalonia and Valencia (CE, LO, EX, AR, GA, IB, M, CN, ML, CL, MU, O, NA, CM, AN, PV)]", "es"));
targetUnits.add(new Unit(8007, "Viernes Santo [Good Friday; National holiday]", "es"));
targetUnits.add(new Unit(8008, "Pascua [Easter Sunday]", "es"));
targetUnits.add(new Unit(8009, "Lunes de Pascua [Easter Monday; Basque Country, Catalonia, Navarra, Valencia]", "es"));
targetUnits.add(new Unit(8010, "Cambio de horario de verano [Begin Daylight Saving Time]", "es"));
targetUnits.add(new Unit(8011, "D\u00eda de Arag\u00f3n [D\u00eda de Arag\u00f3n; cultural, regional; Arag\u00f3n]", "es"));
targetUnits.add(new Unit(8012, "San Jorge [St. George's Day; regional; Aragon and Catalonia]", "es"));
targetUnits.add(new Unit(8013, "D\u00eda de Castilla y Le\u00f3n [Castile and Le\u00f3n Day; cultural, regional; Castile and Le\u00f3n]", "es"));
targetUnits.add(new Unit(8014, "D\u00eda del Trabajador [Labor Day; National holiday]", "es"));
targetUnits.add(new Unit(8015, "Fiesta de la Comunidad [Fiesta de la Comunidad; cultural, regional; Madrid]", "es"));
targetUnits.add(new Unit(8016, "Pentecost\u00e9s [Pentecost]", "es"));
targetUnits.add(new Unit(8017, "Secunda Pascua [Whit Monday]", "es"));
targetUnits.add(new Unit(8018, "D\u00eda de las Letras Gallegas(GA) [Galician Literature Day; regional; Galicia]", "es"));
targetUnits.add(new Unit(8019, "D\u00eda de las Canarias [Canarias Day; cultural, regional; The Canary Islands]", "es"));
targetUnits.add(new Unit(8020, "D\u00eda de la Regi\u00f3n Castilla-La Mancha [Day of Castile-La Mancha; cultural, regional; Castile-La Mancha]", "es"));
targetUnits.add(new Unit(8021, "D\u00eda de la Regi\u00f3n de Murcia [Day of Murcia; cultural, regional, Murcia]", "es"));
targetUnits.add(new Unit(8022, "D\u00eda de La Rioja [Day of Rioja; cultural, regional; La Rioja]", "es"));
targetUnits.add(new Unit(8023, "El Solsticio de junio [June Solstice]", "es"));
targetUnits.add(new Unit(8024, "Sant Joan [St. Joan's Day; regional; Catalonia]", "es"));
targetUnits.add(new Unit(8025, "Santiago Apostol [St. James Day; regional; Basque Country, Galicia]", "es"));
targetUnits.add(new Unit(8026, "Asunci\u00f3n [Assumption of Mary; National holiday]", "es"));
targetUnits.add(new Unit(8027, "D\u00eda de Ceuta [Day of the independent city Ceuta; cultural, regional; City of Ceuta]", "es"));
targetUnits.add(new Unit(8028, "D\u00eda de Asturias y Extremadura [Covadonga and Guadalupe Day; regional; Asturias, Extremadura]", "es"));
targetUnits.add(new Unit(8029, "Fiesta Nacional de Catalu\u00f1a [Day of Catalonia; cultural, regional; Catalonia]", "es"));
targetUnits.add(new Unit(8030, "Equinoccio de septiembre [Autumnal equinox]", "es"));
targetUnits.add(new Unit(8031, "D\u00eda de Valencia [Day of Valencia; cultural, regional; Valencia]", "es"));
targetUnits.add(new Unit(8032, "D\u00eda de la Hispanidad or Fiesta Nacional de Espa\u00f1a [Hispanic Day (Columbus Day); National holiday]", "es"));
targetUnits.add(new Unit(8033, "Cambio de horario de invierno [Daylight Saving Time ends]", "es"));
targetUnits.add(new Unit(8034, "D\u00eda de todos los Santos [All Saints Day; National holiday]", "es"));
targetUnits.add(new Unit(8035, "D\u00eda de la Constituci\u00f3n [Constitution Day; National holiday]", "es"));
targetUnits.add(new Unit(8036, "Inmaculada Concepci\u00f3n [Immaculate Conception; National holiday]", "es"));
targetUnits.add(new Unit(8037, "El Solsticio de diciembre [December Solstice]", "es"));
targetUnits.add(new Unit(8038, "Navidad [Christmas Day; National holiday]", "es"));
targetUnits.add(new Unit(8039, "San Esteban or Segundo d\u00eda de Navidad [St. Stephen's Day; regional; Balearic Islands, Catalonia]", "es"));

// pt
targetUnits.add(new Unit(9000, "Ano Novo [New Year's Day; public holiday]", "pt"));
targetUnits.add(new Unit(9001, "[Carnival]", "pt"));
targetUnits.add(new Unit(9002, "[Begin Daylight Saving Time]", "pt"));
targetUnits.add(new Unit(9003, "Sexta-Feira Santa [Good Friday; public holiday]", "pt"));
targetUnits.add(new Unit(9004, "P\u00e1scoa [Easter Sunday; public holiday]", "pt"));
targetUnits.add(new Unit(9005, "Dia da Liberdade [Freedom Day; public holiday]", "pt"));
targetUnits.add(new Unit(9006, "Dia do Trabalhador [Labour Day; public holiday]", "pt"));
targetUnits.add(new Unit(9007, "Corpo de Deus [Corpus Christi; public holiday]", "pt"));
targetUnits.add(new Unit(9008, "Dia de Portugal, de Cam\u00f5es e das Comunidades Portuguesas [Portugal Day; public holiday]", "pt"));
targetUnits.add(new Unit(9009, "Assun\u00e7\u00e3o de Nossa Senhora [Assumption of Mary; public holiday]", "pt"));
targetUnits.add(new Unit(9010, "Implanta\u00e7\u00e3o da Rep\u00fablica [Republic Day; public holiday]", "pt"));
targetUnits.add(new Unit(9011, "[End Daylight Saving Time]", "pt"));
targetUnits.add(new Unit(9012, "Todos os Santos [All Saint's Day; public holiday]", "pt"));
targetUnits.add(new Unit(9013, "Restaura\u00e7\u00e3o da Independ\u00eancia [Restoration of Independence]", "pt"));
targetUnits.add(new Unit(9014, "Imaculada Concei\u00e7\u00e3o [Immaculate Conception's day; public holiday]", "pt"));
targetUnits.add(new Unit(9015, "Natal [Christmas Day; public holiday]", "pt"));

// it
targetUnits.add(new Unit(10000, "Capodanno [New Year's Day; public holiday]", "it"));
targetUnits.add(new Unit(10001, "Epifania [Epiphany; public holiday]", "it"));
targetUnits.add(new Unit(10002, "[Begin Daylight Saving Time]", "it"));
targetUnits.add(new Unit(10003, "Pasqua [Easter Sunday; public holiday]", "it"));
targetUnits.add(new Unit(10004, "Luned\u00ec dell'Angelo, Pasquetta [Easter Monday; public holiday]", "it"));
targetUnits.add(new Unit(10005, "Festa della Liberazione [Anniversary of Liberation; public holiday]", "it"));
targetUnits.add(new Unit(10006, "Festa dei Lavoratori [Labor Day; public holiday]", "it"));
targetUnits.add(new Unit(10007, "Festa della Repubblica [Republic Day; public holiday]", "it"));
targetUnits.add(new Unit(10008, "San Giovanni", "it"));
targetUnits.add(new Unit(10009, "Assunzione (Ferragosto) [Ferragosto/Assumption Day; public holiday]", "it"));
targetUnits.add(new Unit(10010, "[End Daylight Saving Time]", "it"));
targetUnits.add(new Unit(10011, "Ognissanti (Tutti i Santi) [All Saints; public holiday]", "it"));
targetUnits.add(new Unit(10012, "Il giorno dei morti [Day of the dead; public holiday]", "it"));
targetUnits.add(new Unit(10013, "Giorno della Vittoria [Victory Day; public holiday]", "it"));
targetUnits.add(new Unit(10014, "Immacolata Concezione (Immacolata) [Immaculate Conception; public holiday]", "it"));
targetUnits.add(new Unit(10015, "Natale [Christmas Day; public holiday]", "it"));
targetUnits.add(new Unit(10016, "Santo Stefano [St Stephen's Day; public holiday]", "it"));
targetUnits.add(new Unit(10017, "San Silvestro [New Year's Eve]", "it"));

// gb
targetUnits.add(new Unit(11000, "New Year's Day [bank holiday]", "gb"));
targetUnits.add(new Unit(11001, "2nd January [bank holiday; Scotland only]", "gb"));
targetUnits.add(new Unit(11002, "Burns Night (Burns Supper) [local; Scotland only]", "gb"));
targetUnits.add(new Unit(11003, "St. Patrick's Day [bank holiday; Northern Ireland only]", "gb"));
targetUnits.add(new Unit(11004, "Good Friday [bank holiday]", "gb"));
targetUnits.add(new Unit(11005, "Easter Sunday", "gb"));
targetUnits.add(new Unit(11006, "Easter Monday [bank holiday; except Scotland]", "gb"));
targetUnits.add(new Unit(11007, "Saint David's Day [local; Wales, Catholics]", "gb"));
targetUnits.add(new Unit(11008, "Saint David's Day [local; Wales, Anglican Church]", "gb"));
targetUnits.add(new Unit(11009, "Mothering Sunday [local]", "gb"));
targetUnits.add(new Unit(11010, "Saint Patrick's Day [local; Northern Ireland]", "gb"));
targetUnits.add(new Unit(11011, "Vernal Equinox", "gb"));
targetUnits.add(new Unit(11012, "Begin Daylight Saving Time", "gb"));
targetUnits.add(new Unit(11013, "St. George's Day [National day of England]", "gb"));
targetUnits.add(new Unit(11014, "Royal Wedding (of Prince William of Wales and Catherine Middleton) [bank holiday]", "gb"));
targetUnits.add(new Unit(11015, "May Day Bank Holiday [bank holiday]", "gb"));
targetUnits.add(new Unit(11016, "Whit Monday [bank holiday until 1970; in England, Wales, Northern Ireland]", "gb", false));
targetUnits.add(new Unit(11017, "Spring Bank Holiday [bank holiday]", "gb"));
targetUnits.add(new Unit(11018, "Golden Jubilee of Elizabeth II [bank holiday]", "gb", false));
targetUnits.add(new Unit(11019, "Queen's Diamond Jubilee [bank holiday]", "gb"));
targetUnits.add(new Unit(11020, "Father's Day", "gb"));
targetUnits.add(new Unit(11021, "June Solstice", "gb"));
targetUnits.add(new Unit(11022, "Battle of the Boyne - Orangemen's Day [bank holiday; Northern Ireland only]", "gb"));
targetUnits.add(new Unit(11023, "Summer Bank Holiday [bank holiday; in Scotland only]", "gb"));
targetUnits.add(new Unit(11024, "Summer Bank Holiday [bank holiday; in England, Wales and Northern Ireland]", "gb"));
targetUnits.add(new Unit(11025, "Autumnal equinox", "gb"));
targetUnits.add(new Unit(11026, "Daylight Saving Time ends", "gb"));
targetUnits.add(new Unit(11027, "Halloween", "gb"));
targetUnits.add(new Unit(11028, "Guy Fawkes Night (Bonfire Night)", "gb"));
targetUnits.add(new Unit(11029, "St. Andrew's Day [bank holiday; Scotland only]", "gb"));
targetUnits.add(new Unit(11030, "Autumnal equinox", "gb"));
targetUnits.add(new Unit(11031, "Christmas Day [bank holiday]", "gb"));
targetUnits.add(new Unit(11032, "Boxing Day, St. Stephen's Day [bank holiday]", "gb"));

// ie
targetUnits.add(new Unit(12000, "L\u00e1 Caille / New Year's Day [public holiday]", "ie"));
targetUnits.add(new Unit(12001, "L\u00e1 Fh\u00e9ile P\u00e1draig / St. Patrick's Day [public holiday]", "ie"));
targetUnits.add(new Unit(12002, "Begin Daylight Saving Time", "ie"));
targetUnits.add(new Unit(12003, "Mothering Sunday [local]", "ie"));
targetUnits.add(new Unit(12004, "Domhnach C\u00e1sca / Easter", "ie"));
targetUnits.add(new Unit(12005, "Luan C\u00e1sca / Easter Monday [public holiday]", "ie"));
targetUnits.add(new Unit(12006, "L\u00e1 an Lucht Oibre", "ie"));
targetUnits.add(new Unit(12007, "L\u00e1 Saoire i m\u00ed Mheitheamh / June Holiday [public holiday]", "ie"));
targetUnits.add(new Unit(12008, "Bloomsday", "ie"));
targetUnits.add(new Unit(12009, "L\u00e1 Saoire i m\u00ed L\u00fanasa / August Holiday [public holiday]", "ie"));
targetUnits.add(new Unit(12010, "End Daylight Saving Time", "ie"));
targetUnits.add(new Unit(12011, "L\u00e1 Saoire i m\u00ed Dheireadh F\u00f3mhair / October Holiday [public holiday]", "ie"));
targetUnits.add(new Unit(12012, "L\u00e1 Nollag / Christmas Day [public holiday]", "ie"));
targetUnits.add(new Unit(12013, "L\u00e1 Fh\u00e9ile Stiof\u00e1in or L\u00e1 an Dreoil\u00edn / St. Stephen's Day [public holiday]", "ie"));
targetUnits.add(new Unit(12014, "Equinox March", "ie"));
targetUnits.add(new Unit(12015, "Solstice June", "ie"));
targetUnits.add(new Unit(12016, "Equinox September", "ie"));
targetUnits.add(new Unit(12017, "Solstice December", "ie"));

// se
targetUnits.add(new Unit(13000, "Ny\u00e5rsdagen [New Year's Day; public holiday]", "se"));
targetUnits.add(new Unit(13001, "Trettondagsafton [Twelfth Night; de facto half holiday]", "se"));
targetUnits.add(new Unit(13002, "Trettondedag jul [Epiphany; public holiday]", "se"));
targetUnits.add(new Unit(13003, "Tjugondedag Knut [St. Knut's Day]", "se"));
targetUnits.add(new Unit(13004, "Karlsdag (Kungens namnsdag)", "se"));
targetUnits.add(new Unit(13005, "Alla hj\u00e4rtans dag", "se"));
targetUnits.add(new Unit(13006, "Vasaloppet (Wasalauf)", "se"));
targetUnits.add(new Unit(13007, "Victoriadag (Kronprizessans namsdag)", "se"));
targetUnits.add(new Unit(13008, "Marie Beb\u00e5delsedag", "se"));
targetUnits.add(new Unit(13009, "Sk\u00e4rtorsdagen [Maundy Thursday; de facto half holiday]", "se"));
targetUnits.add(new Unit(13010, "L\u00e5ngfredagen [Good Friday; public holiday]", "se"));
targetUnits.add(new Unit(13011, "P\u00e5skdagen [Easter Sunday; public holiday]", "se"));
targetUnits.add(new Unit(13012, "Annandag p\u00e5sk [Easter Monday; public holiday]", "se"));
targetUnits.add(new Unit(13013, "F\u00f6rsta maj [May Day; public holiday]", "se"));
targetUnits.add(new Unit(13014, "Kristi himmelsf\u00e4rdsdag [Ascension Day; public holiday]", "se"));
targetUnits.add(new Unit(13015, "Pingstafton [Pentecost Eve; de facto half holiday]", "se"));
targetUnits.add(new Unit(13016, "Pingstdagen [Pentecost; public holiday]", "se"));
targetUnits.add(new Unit(13017, "Annandag pingst [Whit Monday]", "se"));
targetUnits.add(new Unit(13018, "Sveriges nationaldag / svenska flaggans dag [National Day of Sweden / Swedish Flag Day; public holiday]", "se"));
targetUnits.add(new Unit(13019, "[Begin Daylight Saving Time]", "se"));
targetUnits.add(new Unit(13020, "[End Daylight Saving Time]", "se"));
targetUnits.add(new Unit(13021, "Valborgsm\u00e4ssoafton [Walpurgis Night; de facto half holiday]", "se"));
targetUnits.add(new Unit(13022, "Morsdag", "se"));
targetUnits.add(new Unit(13023, "Sveriges nationaldag [National Day of Sweden; public holiday]", "se"));
targetUnits.add(new Unit(13024, "Midsommarafton [Midsummer's Eve; de facto full holiday]", "se"));
targetUnits.add(new Unit(13025, "Midsommardagen [Midsummer's Day; public holiday]", "se"));
targetUnits.add(new Unit(13026, "Kronprinzessan Victorias f\u00f6delsedag", "se"));
targetUnits.add(new Unit(13027, "Kr\u00e4ftskiva [Crayfish Party]", "se"));
targetUnits.add(new Unit(13028, "Sk\u00f6rdefest", "se"));
targetUnits.add(new Unit(13029, "Allhelgonaafton [All Saints' Eve; de facto half holiday]", "se"));
targetUnits.add(new Unit(13030, "Alla helgons dag [All Saints' Day; public holiday]", "se"));
targetUnits.add(new Unit(13031, "Gustav Adolfdagen", "se"));
targetUnits.add(new Unit(13032, "M\u00e5rtensg\u00e5s", "se"));
targetUnits.add(new Unit(13033, "Fars dag", "se"));
targetUnits.add(new Unit(13034, "1. Advent", "se"));
targetUnits.add(new Unit(13035, "Nobeldagen", "se"));
targetUnits.add(new Unit(13036, "2. Advent", "se"));
targetUnits.add(new Unit(13037, "Luciadagen", "se"));
targetUnits.add(new Unit(13038, "3. Advent (Gaudete)", "se"));
targetUnits.add(new Unit(13039, "4. Advent", "se"));
targetUnits.add(new Unit(13040, "Drottnings f\u00f6delsedag", "se"));
targetUnits.add(new Unit(13041, "Julafton [Christmas Eve; de facto full holiday]", "se"));
targetUnits.add(new Unit(13042, "Juldagen [Christmas Day; public holiday]", "se"));
targetUnits.add(new Unit(13043, "Annandag jul [Boxing Day; public holiday]", "se"));
targetUnits.add(new Unit(13044, "Ny\u00e5rsafton [New Year's Eve; de facto full holiday]", "se"));
targetUnits.add(new Unit(13045, "V\u00e5rdagj\u00e4mningen Mars [Equinox March]", "se"));
targetUnits.add(new Unit(13046, "Sommar Juni [Solstice June]", "se"));
targetUnits.add(new Unit(13047, "H\u00f6stdagj\u00e4mningen September [Equinox September]", "se"));
targetUnits.add(new Unit(13048, "Vinter December [Solstice December]", "se"));

// fi
targetUnits.add(new Unit(14000, "Uudenvuodenp\u00e4iv\u00e4 [New Year's Day; public]", "fi"));
targetUnits.add(new Unit(14001, "Loppiainen [Epiphany; public]", "fi"));
targetUnits.add(new Unit(14002, "nuutinp\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14003, "J. L. Runebergin p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14004, "Laskiainen", "fi"));
targetUnits.add(new Unit(14005, "Kalevalan p\u00e4iv\u00e4 [Kalevalatag]", "fi"));
targetUnits.add(new Unit(14006, "Pitk\u00e4perjantai [Good Friday; public]", "fi"));
targetUnits.add(new Unit(14007, "P\u00e4\u00e4si\u00e4isp\u00e4iv\u00e4 [Easter Sunday; public]", "fi"));
targetUnits.add(new Unit(14008, "2. P\u00e4\u00e4si\u00e4isp\u00e4iv\u00e4 [Easter Monday; public]", "fi"));
targetUnits.add(new Unit(14009, "Vappu [May Day; public]", "fi"));
targetUnits.add(new Unit(14010, "Mikael Agricolan p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14011, "kansallinen veteraanip\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14012, "J. V. Snellmanin p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14013, "Floratag", "fi"));
targetUnits.add(new Unit(14014, "[Mother's Day]", "fi"));
targetUnits.add(new Unit(14015, "[Memorial Day]", "fi"));
targetUnits.add(new Unit(14016, "Helatorstai [Ascension Day; public]", "fi"));
targetUnits.add(new Unit(14017, "Helluntaip\u00e4iv\u00e4 [Pentecost; public]", "fi"));
targetUnits.add(new Unit(14018, "puolustusvoimain lippujuhlan p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14019, "Juhannusaatto [Midsummer's Eve; de facto full holiday]", "fi"));
targetUnits.add(new Unit(14020, "Suomen lipun p\u00e4iv\u00e4 [Midsummer's Day; public holiday]", "fi"));
targetUnits.add(new Unit(14021, "Eino-Leino-Tag", "fi"));
targetUnits.add(new Unit(14022, "Aleksis Kiven p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14023, "Yhdistyneiden Kansakuntien p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14024, "[All Saints' Day; public holiday]", "fi"));
targetUnits.add(new Unit(14025, "ruotsalaisuuden p\u00e4iv\u00e4", "fi"));
targetUnits.add(new Unit(14026, "Is\u00e4np\u00e4iv\u00e4 [Father's Day]", "fi"));
targetUnits.add(new Unit(14027, "Itsen\u00e4isyysp\u00e4iv\u00e4 [Independence Day; public]", "fi"));
targetUnits.add(new Unit(14028, "Lucia-neito", "fi"));
targetUnits.add(new Unit(14029, "Jouluaatto [Christmas Eve; non official, de facto full holiday]", "fi"));
targetUnits.add(new Unit(14030, "Joulup\u00e4iv\u00e4 [Christmas Day; public]", "fi"));
targetUnits.add(new Unit(14031, "Tapaninp\u00e4iv\u00e4 [St. Stephen's Day; public]", "fi"));
targetUnits.add(new Unit(14032, "[Begin Daylight Saving Time]", "fi"));
targetUnits.add(new Unit(14033, "[End Daylight Saving Time]", "fi"));

// no
targetUnits.add(new Unit(15000, "Nytt\u00e5rsdag [New Year's Day; public holiday]", "no"));
targetUnits.add(new Unit(15001, "[Begin Daylight Saving Time]", "no"));
targetUnits.add(new Unit(15002, "Palmes\u00f8ndag [Palm Sunday]", "no"));
targetUnits.add(new Unit(15003, "Skj\u00e6rtorsdag [Maundy Thursday]", "no"));
targetUnits.add(new Unit(15004, "Langfredag [Good Friday; public holiday]", "no"));
targetUnits.add(new Unit(15005, "F\u00f8rste p\u00e5skedag [Easter Sunday; public holiday]", "no"));
targetUnits.add(new Unit(15006, "Andre p\u00e5skedag [Easter Monday; public holiday]", "no"));
targetUnits.add(new Unit(15007, "F\u00f8rste mai [Labour Day; public holiday]", "no"));
targetUnits.add(new Unit(15008, "Morsdag [Mother's Day]", "no"));
targetUnits.add(new Unit(15009, "Syttende mai or Grunnlovsdagen [Constitution Day; public holiday]", "no"));
targetUnits.add(new Unit(15010, "Kristi himmelfartsdag [Ascension Day; public holiday]", "no"));
targetUnits.add(new Unit(15011, "F\u00f8rste pinsedag [Whit Sunday; public holiday]", "no"));
targetUnits.add(new Unit(15012, "[Whit Monday; public holiday]", "no"));
targetUnits.add(new Unit(15013, "[End Daylight Saving Time]", "no"));
targetUnits.add(new Unit(15014, "Julaften [Christmas Eve]", "no"));
targetUnits.add(new Unit(15015, "F\u00f8rste juledag [Christmas Day; public holiday]", "no"));
targetUnits.add(new Unit(15016, "Andre juledag [Boxing Day; public holiday]", "no"));

// nl
targetUnits.add(new Unit(16000, "Nieuwjaar [New Year's Day; public holiday]", "nl"));
targetUnits.add(new Unit(16001, "Goede Vrijdag [Good Friday; public holiday]", "nl"));
targetUnits.add(new Unit(16002, "Pasen (Sunday) [Easter Sunday; public holiday]", "nl"));
targetUnits.add(new Unit(16003, "Pasen (Monday) [Easter Monday; public holiday]", "nl"));
targetUnits.add(new Unit(16004, "Prinsessedag [Princess' Day]", "nl", false));
targetUnits.add(new Unit(16005, "Koninginnedag [Queen's day; public holiday]", "nl"));
targetUnits.add(new Unit(16006, "Dodenherdenking [Remembrance of the dead; national holiday]", "nl"));
targetUnits.add(new Unit(16007, "Bevrijdingsdag [Liberation day; public holiday]", "nl"));
targetUnits.add(new Unit(16008, "[Mother's Day]", "nl"));
targetUnits.add(new Unit(16009, "Hemelvaartsdag [Ascension of Jesus; public holiday]", "nl"));
targetUnits.add(new Unit(16010, "Pinksteren (Sunday) [Pentecost (Sunday); public holiday]", "nl"));
targetUnits.add(new Unit(16011, "Pinksteren (Monday) [Pentecost (Monday); public holiday]", "nl"));
targetUnits.add(new Unit(16012, "Sinterklaas [Saint Nicholas' Eve]", "nl"));
targetUnits.add(new Unit(16013, "Eerste Kerstdag [Christmas Day; public holiday]", "nl"));
targetUnits.add(new Unit(16014, "Tweede Kerstdag [Christmas Day; public holiday]", "nl"));
targetUnits.add(new Unit(16015, "[Begin Daylight Saving Time (DST)]", "nl"));
targetUnits.add(new Unit(16016, "[End Daylight Saving Time (DST)]", "nl"));

// dk
targetUnits.add(new Unit(17000, "Nyt\u00e5rsdag [New Year's Day; public]", "dk"));
targetUnits.add(new Unit(17001, "Prinsesse Marys f\u00f8dselsdag [Crown Princess Mary's birthday; other]", "dk"));
targetUnits.add(new Unit(17002, "[Valentine's Day]", "dk"));
targetUnits.add(new Unit(17003, "Fastelavn [Fastelavn; other]", "dk"));
targetUnits.add(new Unit(17004, "For\u00e5rsj\u00e6vnd\u00f8gn [Vernal equinox]", "dk"));
targetUnits.add(new Unit(17005, "Palmes\u00f8ndag [Palm Sunday; public]", "dk"));
targetUnits.add(new Unit(17006, "Sk\u00e6rtorsdag [Maundy Thursday; public]", "dk"));
targetUnits.add(new Unit(17007, "Langfredag [Good Friday; public]", "dk"));
targetUnits.add(new Unit(17008, "P\u00e5skedag [Easter Sunday; public]", "dk"));
targetUnits.add(new Unit(17009, "2. P\u00e5skedag [Easter Monday; public]", "dk"));
targetUnits.add(new Unit(17010, "1. April [April Fools' Day]", "dk"));
targetUnits.add(new Unit(17011, "Danmarks bes\u00e6ttelse [Occupation of Denmark]", "dk"));
targetUnits.add(new Unit(17012, "Dronningens f\u00f8dselsdag [Birthday of Queen Margrethe II]", "dk"));
targetUnits.add(new Unit(17013, "Prinsesse Benedikte f\u00f8dselsdag [Princess Benedikte's birthday]", "dk"));
targetUnits.add(new Unit(17014, "Arbejdernes kampdag / 1. maj [Labour Day]", "dk"));
targetUnits.add(new Unit(17015, "Danmarks befrielse [Liberation day; other]", "dk"));
targetUnits.add(new Unit(17016, "Kronprins Frederiks f\u00f8dselsdag [Birthday of Crown Prince Frederik]", "dk"));
targetUnits.add(new Unit(17017, "Sommertid starter [Begin Daylight Saving Time]", "dk"));
targetUnits.add(new Unit(17018, "Store Bededag [Great Prayer Day; public]", "dk"));
targetUnits.add(new Unit(17019, "Kristi Himmelfartsdag [Ascension Day; public]", "dk"));
targetUnits.add(new Unit(17020, "Pinsedag [Whit Sunday; public]", "dk"));
targetUnits.add(new Unit(17021, "2. pinsedag [Whit Monday; public]", "dk"));
targetUnits.add(new Unit(17022, "Grundlovsdag [Constitution Day; public]", "dk"));
targetUnits.add(new Unit(17023, "Sommersolhverv [June Solstice]", "dk"));
targetUnits.add(new Unit(17024, "Efter\u00e5rsj\u00e6vnd\u00f8gn [Autumnal equinox]", "dk"));
targetUnits.add(new Unit(17025, "Prins Joachims f\u00f8dselsdag [Birthday of Prince Joachim; other]", "dk"));
targetUnits.add(new Unit(17026, "Prins Henriks f\u00f8dselsdag [Birthday of Prince Henrik; other]", "dk"));
targetUnits.add(new Unit(17027, "Valdemarsdag og Genforeningsdag [Day of Valdemar and Reunion day; other]", "dk"));
targetUnits.add(new Unit(17028, "Sankt Hans aften [Saint John's Eve; other]", "dk"));
targetUnits.add(new Unit(17029, "Sommertid slutter [End Daylight Saving Time]", "dk"));
targetUnits.add(new Unit(17030, "Mortensaften [The eve before Saint Martin's day; other]", "dk"));
targetUnits.add(new Unit(17031, "Vintersolhverv [December Solstice]", "dk"));
targetUnits.add(new Unit(17032, "Sankt Lucia [Saint Lucy's Day; other]", "dk"));
targetUnits.add(new Unit(17033, "Juleaftensdag [Christmas Eve; public]", "dk"));
targetUnits.add(new Unit(17034, "1. Juledag [First Day of Christmas; public]", "dk"));
targetUnits.add(new Unit(17035, "2. juledag [Second Day of Christmas; public]", "dk"));
targetUnits.add(new Unit(17036, "Nyt\u00e5rsaften [New Year's Eve]", "dk"));

// be
targetUnits.add(new Unit(18000, "Nieuwjaar / Nouvel An / Neujahr [New Year's Day; plublic holiday]", "be"));
targetUnits.add(new Unit(18001, "Driekoningen / \u00c9piphanie / Erscheinung des Herrn [Epiphany; non official public holiday]", "be"));
targetUnits.add(new Unit(18002, "Valentijnsdag / Saint-Valentin / Valentinstag [Valentine's Day; non official public holiday]", "be"));
targetUnits.add(new Unit(18003, "Pasen / P\u00e2ques / Ostern [Easter; plublic holiday]", "be"));
targetUnits.add(new Unit(18004, "Paasmaandag / Lundi de P\u00e2ques / Ostermontag [Easter Monday; plublic holiday]", "be"));
targetUnits.add(new Unit(18005, "Dag van de arbeid / F\u00eate du Travail / Tag der Arbeit [Labour Day; plublic holiday]", "be"));
targetUnits.add(new Unit(18006, "Feest van de Vlaamse Gemeenschap [Day of the Flemish Community; non plublic holiday; observed in Flemish Community only]", "be"));
targetUnits.add(new Unit(18007, "Onze Lieve Heer hemelvaart / Ascension / Christi Himmelfahrt [Ascension; plublic holiday]", "be"));
targetUnits.add(new Unit(18008, "Pinksteren / Pentec\u00f4te / Pfingsten [Pentecost; plublic holiday]", "be"));
targetUnits.add(new Unit(18009, "Pinkstermaandag / Lundi de Pentec\u00f4te / Pfingstmontag [Pentecost Monday; plublic holiday]", "be"));
targetUnits.add(new Unit(18010, "Nationale feestdag / F\u00eate nationale / Nationalfeiertag [National holiday; plublic holiday]", "be"));
targetUnits.add(new Unit(18011, "F\u00eate de la Communaut\u00e9 fran\u00e7aise de Belgique [Day of the French Community of Belgium; non plublic holiday; in the French Community only]", "be"));
targetUnits.add(new Unit(18012, "Onze Lieve Vrouw hemelvaart / Assomption / Mari\u00e4 Himmelfahrt [Assumption of Mary; plublic holiday]", "be"));
targetUnits.add(new Unit(18013, "Allerheiligen / Toussaint / Allerheiligen [All Saints; public holiday]", "be"));
targetUnits.add(new Unit(18014, "Allerzielen / F\u00eate des morts / Allerseelen [All Soul's Day]", "be"));
targetUnits.add(new Unit(18015, "Wapenstilstand / Jour de l'armistice / Waffenstillstand [Armistice Day; public holiday]", "be"));
targetUnits.add(new Unit(18016, "Tag der Deutschsprachigen Gemeinschaft Belgiens [Day of the German-speaking Community of Belgium; non public holiday; in the German-speaking region only]", "be"));
targetUnits.add(new Unit(18017, "Koningsdag / F\u00eate du Roi / Festtag des K\u00f6nigs [King's Feast; non public holiday]", "be"));
targetUnits.add(new Unit(18018, "Sinterklaas / Saint-Nicolas (f\u00eate) / Sankt Nikolaus [Saint Nicholas; non public holiday]", "be"));
targetUnits.add(new Unit(18019, "Kerstmis / No\u00ebl / Weihnacht [Christmas; plublic holiday]", "be"));

// gr
targetUnits.add(new Unit(19000, "\u03a0\u03c1\u03c9\u03c4\u03bf\u03c7\u03c1\u03bf\u03bd\u03b9\u03ac / Protochronia [New Year's Day; public holiday]", "gr"));
targetUnits.add(new Unit(19001, "\u0398\u03b5\u03bf\u03c6\u03ac\u03bd\u03b5\u03b9\u03b1 / Theofania [Epiphany; public holiday]", "gr"));
targetUnits.add(new Unit(19002, "\u03a4\u03c1\u03b9\u03ce\u03bd \u0399\u03b5\u03c1\u03b1\u03c1\u03c7\u03ce\u03bd / Trion Ierarchon [The Three Holy Hierarchs; school holiday only]", "gr"));
targetUnits.add(new Unit(19003, "\u039a\u03b1\u03b8\u03b1\u03c1\u03ae \u0394\u03b5\u03c5\u03c4\u03ad\u03c1\u03b1 / Kathari Devtera [Ash Monday (eastern churches); public holiday]", "gr"));
targetUnits.add(new Unit(19004, "\u0395\u03c5\u03b1\u03b3\u03b3\u03b5\u03bb\u03b9\u03c3\u03bc\u03cc\u03c2 \u0398\u03b5\u03bf\u03c4\u03cc\u03ba\u03bf\u03c5 / Eikosti-pempti Martiou [25th of March; public holiday]", "gr"));
targetUnits.add(new Unit(19005, "[Begin Daylight Saving Time]", "gr"));
targetUnits.add(new Unit(19006, "\u039a\u03c5\u03c1\u03b9\u03b1\u03ba\u03ae \u03c4\u03bf\u03c5 \u03a0\u03ac\u03c3\u03c7\u03b1 / Kyriaki tou Pascha [Easter Sunday (eastern churches)]", "gr"));
targetUnits.add(new Unit(19007, "\u0394\u03b5\u03c5\u03c4\u03ad\u03c1\u03b1 \u03c4\u03bf\u03c5 \u03a0\u03ac\u03c3\u03c7\u03b1 / Deutera tou Pascha [Easter Monday (eastern churches); public holiday]", "gr"));
targetUnits.add(new Unit(19008, "\u0395\u03c1\u03b3\u03b1\u03c4\u03b9\u03ba\u03ae \u03a0\u03c1\u03c9\u03c4\u03bf\u03bc\u03b1\u03b3\u03b9\u03ac / Ergatiki Protomagia [Labour Day; public holiday]", "gr"));
targetUnits.add(new Unit(19009, "\u03a0\u03bd\u03b5\u03c5\u03bc\u03b1\u03c4\u03bf\u03c2 / Pnevmatos [Pentecost (eastern churches); public holiday]", "gr"));
targetUnits.add(new Unit(19010, "\u0391\u03b3\u03b9\u03bf\u03c5 \u03a0\u03bd\u03b5\u03c5\u03bc\u03b1\u03c4\u03bf\u03c2 / Tou Agiou Pnevmatos [Pentecost Monday (eastern churches); public holiday]", "gr"));
targetUnits.add(new Unit(19011, "\u0397 \u039a\u03bf\u03af\u03bc\u03b7\u03c3\u03b9\u03c2 \u03c4\u03b7\u03c2 \u0398\u03b5\u03bf\u03c4\u03cc\u03ba\u03bf\u03c5 / Panaghias [The Dormition of the Holy Virgin; public holiday]", "gr"));
targetUnits.add(new Unit(19012, "[End Daylight Saving Time]", "gr"));
targetUnits.add(new Unit(19013, "\u03a4\u03bf \u038c\u03c7\u03b9 / To Ochi [The Ochi day; public holiday]", "gr"));
targetUnits.add(new Unit(19014, "\u03a0\u03bf\u03bb\u03c5\u03c4\u03b5\u03c7\u03bd\u03b5\u03af\u03bf / Polytechneio [Polytechneio; school holiday only]", "gr"));
targetUnits.add(new Unit(19015, "\u03a7\u03c1\u03b9\u03c3\u03c4\u03bf\u03cd\u03b3\u03b5\u03bd\u03bd\u03b1 / Christougenna [Christmas Day; public holiday]", "gr"));
targetUnits.add(new Unit(19016, "\u03a3\u03cd\u03bd\u03b1\u03be\u03b9\u03c2 \u0398\u03b5\u03bf\u03c4\u03cc\u03ba\u03bf\u03c5 / Synaxis Theotokou [Boxing Day; public holiday]", "gr"));

// ro
targetUnits.add(new Unit(20000, "Anul nou [New Year's Day; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20001, "2. Anul nou [Day after New Year's Day; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20002, "Ziua Unirii [Unification Day; Official holiday]", "ro"));
targetUnits.add(new Unit(20003, "Dragobetele [St. Valentine's Day; Traditional festival]", "ro"));
targetUnits.add(new Unit(20004, "M\u0103r\u0163i\u015forul [Spring festival; Traditional festival]", "ro"));
targetUnits.add(new Unit(20005, "Ziua Unirii [Women's Day; Official holiday]", "ro"));
targetUnits.add(new Unit(20006, "Ziua Mamei [Mother's Day]", "ro"));
targetUnits.add(new Unit(20007, "Ziua_Tat\u0103lui [Father's Day]", "ro"));
targetUnits.add(new Unit(20008, "Pa\u015ftele [Easter; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20009, "[NATO Day; Not a public holiday]", "ro"));
targetUnits.add(new Unit(20010, "Ziua Eroilor (\u00cen\u0103l\u0163area) [Heroes' Day (Ascension); Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20011, "1. Rusaliile [Pentecost, Whit Monday; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20012, "2. Rusaliile [Whit Monday; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20013, "[Begin Daylight Saving Time]", "ro"));
targetUnits.add(new Unit(20014, "Ziua muncii [Labour Day; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20015, "Ziua Copilului [Child's day; Official holiday]", "ro"));
targetUnits.add(new Unit(20016, "Ziua Tricolorului [Flag Day; Official holiday]", "ro"));
targetUnits.add(new Unit(20017, "Ziua Imnului na\u0163ional [National Anthem Day; Official holiday]", "ro"));
targetUnits.add(new Unit(20018, "Adormirea Maicii Domnului [Dormition of the Theotokos; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20019, "Armed Forces Day [Armed Forces Day; Not a public holiday]", "ro"));
targetUnits.add(new Unit(20020, "Ziua Na\u0163ional\u0103 (Ziua Marii Uniri) [National holiday (Great Union Day); Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20021, "Ziua Constitu\u0163iei [Constitution Day]", "ro"));
targetUnits.add(new Unit(20022, "Cr\u0103ciunul [Christmas Day; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20023, "A doua zi de Cr\u0103ciun [Boxing Day; Official non-working holiday]", "ro"));
targetUnits.add(new Unit(20024, "[End Daylight Saving Time]", "ro"));

// si
targetUnits.add(new Unit(21000, "novo leto [New Year's Day; state holiday, work-free]", "si"));
targetUnits.add(new Unit(21001, "2. novo leto [New Year's Day; state holiday, work-free]", "si"));
targetUnits.add(new Unit(21002, "Pre\u0161ernov dan, slovenski kulturni praznik [The Slovenian Cultural Holiday; state holiday, work-free]", "si"));
targetUnits.add(new Unit(21003, "[Begin Daylight Saving Time]", "si"));
targetUnits.add(new Unit(21004, "velika no\u010d in velikono\u010dni ponedeljek [Easter Sunday; work-free day]", "si"));
targetUnits.add(new Unit(21005, "2. velika no\u010d in velikono\u010dni ponedeljek [Easter Monday; work-free day]", "si"));
targetUnits.add(new Unit(21006, "dan upora proti okupatorju [Day of Uprising Against Occupation; state holiday, work-free]", "si"));
targetUnits.add(new Unit(21007, "praznik dela [Labour Day; state holiday]", "si"));
targetUnits.add(new Unit(21008, "2. praznik dela [2. Labour Day; state holiday]", "si"));
targetUnits.add(new Unit(21009, "binko\u0161tna nedelja [Pentecost; work-free day]", "si"));
targetUnits.add(new Unit(21010, "dan dr\u017eavnosti [Statehood Day; state holiday]", "si"));
targetUnits.add(new Unit(21011, "Marijino vnebovzetje [Assumption Day; work-free day]", "si"));
targetUnits.add(new Unit(21012, "dan zdru\u017eitve prekmurskih Slovencev z mati\u010dnim narodom po prvi svetovni vojni [Union of the Slovenians in Prekmurje with the mother nation after the First World War; state holiday, not work-free]", "si"));
targetUnits.add(new Unit(21013, "[End Daylight Saving Time]", "si"));
targetUnits.add(new Unit(21014, "dan vrnitve Primorske k mati\u010dni domovini [Reunion of Slovenian Littoral with the motherland; state holiday, not work-free]", "si"));
targetUnits.add(new Unit(21015, "dan reformacije [Reformation Day; work-free day]", "si"));
targetUnits.add(new Unit(21016, "dan spomina na mrtve [Remembrance day; state holiday, work-free]", "si"));
targetUnits.add(new Unit(21017, "dan Rudolfa Maistra [Rudolf Maister Day; state holiday, not work-free]", "si"));
targetUnits.add(new Unit(21018, "Bo\u017ei\u010d [Christmas Day; work-free day]", "si"));
targetUnits.add(new Unit(21019, "dan samostojnosti in enotnosti [Independence and Unity Day; state holiday, work free]", "si"));

// pl
targetUnits.add(new Unit(22000, "[New Year's Day]", "pl"));
targetUnits.add(new Unit(22001, "Dzie\u0144 Flagi Rzeczypospolitej Polskiej [Flag of the Republic of Poland Day (Flag Day); non public holiday]", "pl"));
targetUnits.add(new Unit(22002, "pierwszy dzie\u0144 Wielkiej Nocy [Easter Sunday; public holiday]", "pl"));
targetUnits.add(new Unit(22003, "drugi dzie\u0144 Wielkiej Nocy [Easter Monday; public holiday]", "pl"));
targetUnits.add(new Unit(22004, "\u015awi\u0119to Pa\u0144stwowe [State Holiday]", "pl"));
targetUnits.add(new Unit(22005, "\u015awi\u0119to Narodowe Trzeciego Maja [Constitution Day]", "pl"));
targetUnits.add(new Unit(22006, "pierwszy dzie\u0144 Zielonych \u015awi\u0105tek [Pentecost]", "pl"));
targetUnits.add(new Unit(22007, "dzie\u0144 Bo\u017cego Cia\u0142a (Bo\u017ce Cia\u0142o) [Corpus Christi]", "pl"));
targetUnits.add(new Unit(22008, "Wniebowzi\u0119cie Naj\u015bwi\u0119tszej Maryi Panny [Assumption of the Blessed Virgin Mary]", "pl"));
targetUnits.add(new Unit(22009, "Wszystkich \u015awi\u0119tych [All Saint's Day; public holiday]", "pl"));
targetUnits.add(new Unit(22010, "[Independence Day]", "pl"));
targetUnits.add(new Unit(22011, "pierwszy dzie\u0144 Bo\u017cego Narodzenia [1st day of Christmas; public holiday]", "pl"));
targetUnits.add(new Unit(22012, "drugi dzie\u0144 Bo\u017cego Narodzenia [2nd day of Christmas; public holiday]", "pl"));
targetUnits.add(new Unit(22013, "[DST start]", "pl"));
targetUnits.add(new Unit(22014, "[End Daylight Saving Time]", "pl"));
targetUnits.add(new Unit(22015, "[Equinox March]", "pl"));
targetUnits.add(new Unit(22016, "[Solstice June]", "pl"));
targetUnits.add(new Unit(22017, "[Equinox September]", "pl"));
targetUnits.add(new Unit(22018, "[Solstice December]", "pl"));

// ru
targetUnits.add(new Unit(23000, "\u041d\u043e\u0432\u044b\u0439 \u0413\u043e\u0434 / Novy God [New Year's Day; public holiday]", "ru"));
targetUnits.add(new Unit(23001, "\u0420\u043e\u0436\u0434\u0435\u0441\u0442\u0432\u043e / Rozhdestvo [Orthodox Christmas Day; public holiday]", "ru"));
targetUnits.add(new Unit(23002, "\u0414\u0435\u043d\u044c \u0441\u0432\u044f\u0442\u043e\u0433\u043e \u0412\u0430\u043b\u0435\u043d\u0442\u0438\u043d\u0430 [Valentine's Day]", "ru"));
targetUnits.add(new Unit(23003, "\u041c\u0430\u0441\u043b\u0435\u043d\u0438\u0446\u0430 / Maslenitsa [Carnival]", "ru"));
targetUnits.add(new Unit(23004, "\u0414\u0435\u043d\u044c \u0417\u0430\u0449\u0438\u0442\u043d\u0438\u043a\u0430 \u041e\u0442\u0435\u0447\u0435\u0441\u0442\u0432\u0430 / Den' zashchitnika Otechestva [Defender of the Fatherland Day]", "ru"));
targetUnits.add(new Unit(23005, "\u041c\u0435\u0436\u0434\u0443\u043d\u0430\u0440\u043e\u0434\u043d\u044b\u0439 \u0416\u0435\u043d\u0441\u043a\u0438\u0439 \u0414\u0435\u043d\u044c [International Women's Day; public holiday]", "ru"));
targetUnits.add(new Unit(23006, "\u0414\u0435\u043d\u044c \u0432\u0435\u0441\u043d\u044b \u0438 \u0442\u0440\u0443\u0434\u0430 [Spring and Labour Day; public holiday]", "ru"));
targetUnits.add(new Unit(23007, "\u0414\u0435\u043d\u044c \u041f\u043e\u0431\u0435\u0434\u044b [Victory Day; public holiday]", "ru"));
targetUnits.add(new Unit(23008, "\u0414\u0435\u043d\u044c \u0420\u043e\u0441\u0441\u0438\u0438 [Russia Day; public holiday]", "ru"));
targetUnits.add(new Unit(23009, "\u0414\u0435\u043d\u044c \u043d\u0430\u0440\u043e\u0434\u043d\u043e\u0433\u043e \u0435\u0434\u0438\u043d\u0441\u0442\u0432\u0430 [Unity Day; public holiday]", "ru"));
targetUnits.add(new Unit(23010, "\u041f\u0430\u0441\u0445\u0430 [Easter]", "ru"));
targetUnits.add(new Unit(23011, "\u0414\u0435\u043d\u044c \u0417\u043d\u0430\u043c\u0435\u043d\u0438", "ru"));
targetUnits.add(new Unit(23012, "\u0414\u0435\u043d\u044c \u041a\u043e\u043d\u0441\u0442\u0438\u0442\u0443\u0446\u0438\u0438", "ru"));

// am
targetUnits.add(new Unit(24000, "\u0531\u0574\u0561\u0576\u0585\u0580 [New Year's Day; public]", "am"));
targetUnits.add(new Unit(24001, "\u0546\u0561\u056d\u0561\u056e\u0576\u0576\u0564\u0575\u0561\u0576 \u057f\u0578\u0576\u0565\u0580 [Christmas Eve; public]", "am"));
targetUnits.add(new Unit(24002, "\u054d\u0578\u0582\u0580\u0562 \u053e\u0576\u0578\u0582\u0576\u0564 [Christmas Day; public]", "am"));
targetUnits.add(new Unit(24003, "\u054d\u0578\u0582\u0580\u0562 \u053e\u0576\u0576\u0564\u0575\u0561\u0576 \u0587 \u0540\u0561\u0575\u057f\u0576\u0578\u0582\u0569\u0575\u0561\u0576 \u057f\u0578\u0576\u056b\u0576 \u0570\u0561\u057b\u0578\u0580\u0564\u0578\u0572` \u0544\u0565\u057c\u0565\u056c\u0578\u0581 \u0570\u056b\u0577\u0561\u057f\u0561\u056f\u056b \u0585\u0580 [The day of commemoration of all dead people after Christmas Day; public]", "am"));
targetUnits.add(new Unit(24004, "\u0532\u0561\u0576\u0561\u056f\u056b \u0585\u0580 [Army Day; public]", "am"));
targetUnits.add(new Unit(24005, "\u053f\u0561\u0576\u0561\u0576\u0581 \u057f\u0578\u0576 [International Women's Day; public]", "am"));
targetUnits.add(new Unit(24006, "\u0535\u0572\u0565\u057c\u0576\u056b \u0566\u0578\u0570\u0565\u0580\u056b \u0570\u056b\u0577\u0561\u057f\u0561\u056f\u056b \u0585\u0580 [Genocide Remembrance Day; public]", "am"));
targetUnits.add(new Unit(24007, "\u0540\u0561\u0572\u0569\u0561\u0576\u0561\u056f\u056b \u0565\u0582 \u053d\u0561\u0572\u0561\u0572\u0578\u0582\u0569\u0575\u0561\u0576 \u057f\u0578\u0576 [Victory and Peace Day; public]", "am"));
targetUnits.add(new Unit(24008, "\u0540\u0561\u0576\u0580\u0561\u057a\u0565\u057f\u0578\u0582\u0569\u0575\u0561\u0576 \u0585\u0580 [Republic Day; public]", "am"));
targetUnits.add(new Unit(24009, "\u054d\u0561\u0570\u0574\u0561\u0576\u0561\u0564\u0580\u0578\u0582\u0569\u0575\u0561\u0576 \u0585\u0580 [Constitution Day; public]", "am"));
targetUnits.add(new Unit(24010, "\u0531\u0576\u056f\u0561\u056d\u0578\u0582\u0569\u0575\u0561\u0576 \u0585\u0580 [Independence Day; public]", "am"));
targetUnits.add(new Unit(24011, "\u0535\u0580\u056f\u0580\u0561\u0577\u0561\u0580\u056a\u056b \u0566\u0578\u0570\u0565\u0580\u056b \u0570\u056b\u0577\u0561\u057f\u0561\u056f\u056b \u0585\u0580 [1988 Earthquake Memorial Day (Spitak Remembrance Day); public]", "am"));
targetUnits.add(new Unit(24012, "\u054e\u0561\u0580\u0564\u0587\u0561\u0580 [Vardevar]", "am"));

// mx
targetUnits.add(new Unit(25000, "A\u00f1o Nuevo [New Year's Day; statutory holiday]", "mx"));
targetUnits.add(new Unit(25001, "D\u00eda de la Constituci\u00f3n [Constitution day; statutory holiday]", "mx"));
targetUnits.add(new Unit(25002, "Natalicio de Benito Ju\u00e1rez [Benito Ju\u00e1rez's birthday; statutory holiday]", "mx"));
targetUnits.add(new Unit(25003, "D\u00eda del Trabajo [Labour Day; statutory holiday]", "mx"));
targetUnits.add(new Unit(25004, "D\u00eda de Independencia [Independence Day; statutory holiday]", "mx"));
targetUnits.add(new Unit(25005, "D\u00eda de la Revoluci\u00f3n [Revolution day; statutory holiday]", "mx"));
targetUnits.add(new Unit(25006, "Transmisi\u00f3n del Poder Ejecutivo Federal [Change of Federal Government; statutory holiday]", "mx"));
targetUnits.add(new Unit(25007, "Navidad [Christmas' Day; statutory holiday]", "mx"));
targetUnits.add(new Unit(25008, "D\u00eda del Ej\u00e9rcito [Army's Day; civic holiday]", "mx"));
targetUnits.add(new Unit(25009, "D\u00eda de la Bandera [Flag Day; civic holiday]", "mx"));
targetUnits.add(new Unit(25010, "Aniversario de la Expropiaci\u00f3n petrolera [Anniversary of the Oil Expropriation; civic holiday]", "mx"));
targetUnits.add(new Unit(25011, "Heroica Defensa de Veracruz [Heroic Defense of Veracruz; civic holiday]", "mx"));
targetUnits.add(new Unit(25012, "Batalla de Puebla [Cinco de Mayo; civic holiday]", "mx"));
targetUnits.add(new Unit(25013, "Natalicio de Miguel Hidalgo [Miguel Hidalgo's birthday; civic holiday]", "mx"));
targetUnits.add(new Unit(25014, "D\u00eda de la Marina [Marine's Day; civic holiday]", "mx"));
targetUnits.add(new Unit(25015, "D\u00eda de los Ni\u00f1os H\u00e9roes ['Boy Heroes' or 'Heroic Cadets'; civic holiday]", "mx"));
targetUnits.add(new Unit(25016, "Grito de Dolores [Cry of Dolores; civic holiday]", "mx"));
targetUnits.add(new Unit(25017, "Consumaci\u00f3n de la Independencia [End of Independence War; civic holiday]", "mx"));
targetUnits.add(new Unit(25018, "Natalicio de Jos\u00e9 Ma. Morelos y Pav\u00f3n [Morelos' birthday; civic holiday]", "mx"));
targetUnits.add(new Unit(25019, "Descubrimiento de Am\u00e9rica y D\u00eda de la Raza [Columbus Day; civic holiday]", "mx"));
targetUnits.add(new Unit(25020, "D\u00eda de los Santos Reyes [Epiphany; Festivity]", "mx"));
targetUnits.add(new Unit(25021, "D\u00eda de San Valent\u00edn [Valentine's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25022, "D\u00eda del Ni\u00f1o [Children's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25023, "D\u00eda de las Madres [Mother's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25024, "D\u00eda del Maestro [Teacher's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25025, "D\u00eda del estudiante [Student's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25026, "D\u00eda del Padre [Father's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25027, "[All Saint's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25028, "[All Soul's Day; Festivity]", "mx"));
targetUnits.add(new Unit(25029, "D\u00eda de la Virgen de Guadalupe [Day of the Virgin of Guadalupe; Festivity]", "mx"));
targetUnits.add(new Unit(25030, "Las Posadas [The Inns; Festivity]", "mx"));
targetUnits.add(new Unit(25031, "Nochebuena [Christmas Eve; Festivity]", "mx"));
targetUnits.add(new Unit(25032, "Dia de los Santos Inocentes [Day of the Innocents; Festivity]", "mx"));
targetUnits.add(new Unit(25033, "[Begin Daylight Saving Time (DST)]", "mx"));
targetUnits.add(new Unit(25034, "[End Daylight Saving Time (DST)]", "mx"));

// br
targetUnits.add(new Unit(26000, "Confraterniza\u00e7\u00e3o Universal [New Year's Day; public holiday]", "br"));
targetUnits.add(new Unit(26001, "Carnaval [Carnival festivities begin; public holiday]", "br"));
targetUnits.add(new Unit(26002, "Quarta-feira de Cinzas [Ash Wednesday]", "br"));
targetUnits.add(new Unit(26003, "Sexta-Feira Santa [Good Friday; public holiday]", "br"));
targetUnits.add(new Unit(26004, "P\u00e1scoa", "br"));
targetUnits.add(new Unit(26005, "Tiradentes [Tiradentes; public holiday]", "br"));
targetUnits.add(new Unit(26006, "Dia do Trabalho [Labour Day; public holiday]", "br"));
targetUnits.add(new Unit(26007, "Dia das M\u00e3es [Mother's Day]", "br"));
targetUnits.add(new Unit(26008, "Dia dos Namorados [Valentine's Day]", "br"));
targetUnits.add(new Unit(26009, "[Corpus Christi; public holiday]", "br"));
targetUnits.add(new Unit(26010, "Dia dos Pais [Father's Day]", "br"));
targetUnits.add(new Unit(26011, "Dia do Soldado [Soldier's Day]", "br"));
targetUnits.add(new Unit(26012, "Dia da Independ\u00eancia [Independence Day; public holiday]", "br"));
targetUnits.add(new Unit(26013, "Nossa Senhora de Aparecida [Our Lady of Aparecida; public holiday, catholic]", "br"));
targetUnits.add(new Unit(26014, "Dia de Todos-os-Santos", "br"));
targetUnits.add(new Unit(26015, "Dia de Finados [All Soul's Day; public holiday]", "br"));
targetUnits.add(new Unit(26016, "Proclama\u00e7\u00e3o da Rep\u00fablica [Proclamation of the Republic Day; public holiday]", "br"));
targetUnits.add(new Unit(26017, "Natal [Christmas Day; public holiday]", "br"));
targetUnits.add(new Unit(26018, "V\u00e9spera de Ano Novo [New Year's Eve]", "br"));

// ve
targetUnits.add(new Unit(27000, "D\u00eda de A\u00f1o Nuevo [New Year's Day; public holiday]", "ve"));
targetUnits.add(new Unit(27001, "D\u00eda de Reyes [Epiphany; public holiday]", "ve"));
targetUnits.add(new Unit(27002, "Carnaval [Carnival; public holiday]", "ve"));
targetUnits.add(new Unit(27003, "Semana Santa (Start) [Holy Week Start; public holiday]", "ve"));
targetUnits.add(new Unit(27004, "Semana Santa (End) [Holy Week End; public holiday]", "ve"));
targetUnits.add(new Unit(27005, "Jueves Santo [Maundy Thursday]", "ve"));
targetUnits.add(new Unit(27006, "Viernes Santo [Good Friday]", "ve"));
targetUnits.add(new Unit(27007, "Pascua [Easter Sunday]", "ve"));
targetUnits.add(new Unit(27008, "Lunes de Pascua [Easter Monday]", "ve"));
targetUnits.add(new Unit(27009, "[Easter Extra Day I]", "ve"));
targetUnits.add(new Unit(27010, "[Easter Extra Day II]", "ve"));
targetUnits.add(new Unit(27011, "[Easter Extra Day III]", "ve"));
targetUnits.add(new Unit(27012, "D\u00eda de San Jos\u00e9 [Saint Joseph's Day; public holiday]", "ve"));
targetUnits.add(new Unit(27013, "19 de abril [Beginning of the Independence Movement; public holiday]", "ve"));
targetUnits.add(new Unit(27014, "D\u00eda del Trabajador [Labour Day; public holiday]", "ve"));
targetUnits.add(new Unit(27015, "Batalla de Carabobo [Battle of Carabobo; public holiday]", "ve"));
targetUnits.add(new Unit(27016, "5 de julio [Independence Day; public holiday]", "ve"));
targetUnits.add(new Unit(27017, "Natalicio del Libertador [Birth of Sim\u00f3n Bol\u00edvar; public holiday]", "ve"));
targetUnits.add(new Unit(27018, "D\u00eda de la Bandera [Flag Day; public holiday]", "ve"));
targetUnits.add(new Unit(27019, "D\u00eda de la Raza [Columbus Day; public holiday]", "ve", false));
targetUnits.add(new Unit(27020, "D\u00eda de la Resistencia Ind\u00edgena [Day of Indigenous Resistance; public holiday]", "ve"));
targetUnits.add(new Unit(27021, "D\u00eda de Todos los Santos [All Saints Day; public holiday]", "ve"));
targetUnits.add(new Unit(27022, "Feria de la Chinita [Feria of La Chinita; public holiday; only in the Zulian region (3 days)]", "ve"));
targetUnits.add(new Unit(27023, "Inmaculada Concepci\u00f3n [Immaculate Conception; public holiday]", "ve"));
targetUnits.add(new Unit(27024, "Nochebuena [Christmas Eve; public holiday]", "ve"));
targetUnits.add(new Unit(27025, "Nochevieja [New Year's Eve; public holiday]", "ve"));
targetUnits.add(new Unit(27026, "[Begin Daylight Saving Time (DST)]", "ve"));
targetUnits.add(new Unit(27027, "[End Daylight Saving Time (DST)]", "ve"));

// pa
targetUnits.add(new Unit(28000, "[New Year's Day; public holiday]", "pa"));
targetUnits.add(new Unit(28001, "[Martyrs' Day; public holiday]", "pa"));
targetUnits.add(new Unit(28002, "[Carnival's Monday; public holiday]", "pa"));
targetUnits.add(new Unit(28003, "[Carnival's Tuesday; public holiday]", "pa"));
targetUnits.add(new Unit(28004, "[Holy Friday; public holiday]", "pa"));
targetUnits.add(new Unit(28005, "[Easter]", "pa"));
targetUnits.add(new Unit(28006, "[May Day; public holiday]", "pa"));
targetUnits.add(new Unit(28007, "[Anniversary of the Founding of  Panama City]", "pa"));
targetUnits.add(new Unit(28008, "[Separation Day (from Colombia); public holiday]", "pa"));
targetUnits.add(new Unit(28009, "[Flag Day; public holiday]", "pa"));
targetUnits.add(new Unit(28010, "[Colon Day; public holiday]", "pa"));
targetUnits.add(new Unit(28011, "Primer Grito de Independencia de la Villa de los Santos [The uprise in the Villa de los Santos against Spain; public holiday]", "pa"));
targetUnits.add(new Unit(28012, "[Independence Day (from Spain); public holiday]", "pa"));
targetUnits.add(new Unit(28013, "[Mothers' Day; public holiday]", "pa"));
targetUnits.add(new Unit(28014, "[Christmas; public holiday]", "pa"));
targetUnits.add(new Unit(28015, "[Begin Daylight Saving Time (DST)]", "pa"));
targetUnits.add(new Unit(28016, "[End Daylight Saving Time (DST)]", "pa"));

// co
targetUnits.add(new Unit(29000, "A\u00f1o Nuevo [New Year's Day; public holiday]", "co"));
targetUnits.add(new Unit(29001, "D\u00eda de los Reyes Magos [Epiphany; public holiday]", "co"));
targetUnits.add(new Unit(29002, "D\u00eda del Maestro [Teacher's Day; public holiday]", "co"));
targetUnits.add(new Unit(29003, "D\u00eda de San Jos\u00e9 [Saint Joseph's Day; public holiday]", "co"));
targetUnits.add(new Unit(29004, "[Jueves Santo; public holiday]", "co"));
targetUnits.add(new Unit(29005, "Viernes Santo [Good Friday; public holiday]", "co"));
targetUnits.add(new Unit(29006, "Primero de Mayo [Labor Day; public holiday]", "co"));
targetUnits.add(new Unit(29007, "Ascensi\u00f3n [Ascension; public holiday]", "co"));
targetUnits.add(new Unit(29008, "[Corpus Christi; public holiday]", "co"));
targetUnits.add(new Unit(29009, "Sagrado Coraz\u00f3n [Sacred Heart; public holiday]", "co"));
targetUnits.add(new Unit(29010, "San Pedro y San Pablo [Saint Peter and Saint Paul; public holiday]", "co"));
targetUnits.add(new Unit(29011, "[Independence Day; public holiday]", "co"));
targetUnits.add(new Unit(29012, "[Battle of Boyac\u00e1; public holiday]", "co"));
targetUnits.add(new Unit(29013, "La Asunci\u00f3n [Assumption of Mary; public holiday]", "co"));
targetUnits.add(new Unit(29014, "D\u00eda de la Raza [Columbus Day; public holiday]", "co"));
targetUnits.add(new Unit(29015, "[All Saints' Day; public holiday]", "co"));
targetUnits.add(new Unit(29016, "[Independence of Cartagena City; public holiday]", "co"));
targetUnits.add(new Unit(29017, "La Inmaculada Concepci\u00f3n [Immaculate Conception; public holiday]", "co"));
targetUnits.add(new Unit(29018, "Navidad [Christmas Day; public holiday]", "co"));
targetUnits.add(new Unit(29019, "[Begin Daylight Saving Time (DST)]", "co"));
targetUnits.add(new Unit(29020, "[End Daylight Saving Time (DST)]", "co"));

// za
targetUnits.add(new Unit(30000, "Uncibijane / Umhla Wonyaka Omtsha / Nuwejaarsdag / Let\u0161at\u0161i la Ngwaga o Moswa / Letsatsi la Ngwaga o Mosha / New Year's Day / Selemo se Setjha / Siku ra Lembe lerintshwa / Lilanga Lemnyaka Lomusha /  / ILanga loNyaka omuTjha [public holiday]", "za"));
targetUnits.add(new Unit(30001, " /  /  /  /  / Human Rights Day /  /  /  /  /  [public holiday]", "za"));
targetUnits.add(new Unit(30002, "Good Friday [public holiday]", "za"));
targetUnits.add(new Unit(30003, "Easter Sunday", "za"));
targetUnits.add(new Unit(30004, "Easter Monday", "za", false));
targetUnits.add(new Unit(30005, "Family Day", "za"));
targetUnits.add(new Unit(30006, "Van Riebeeck's Day", "za", false));
targetUnits.add(new Unit(30007, "Founder's Day", "za", false));
targetUnits.add(new Unit(30008, "Freedom Day [public holiday]", "za"));
targetUnits.add(new Unit(30009, "Workers' Day [public holiday]", "za"));
targetUnits.add(new Unit(30010, "Youth Day [public holiday]", "za"));
targetUnits.add(new Unit(30011, "National Women's Day [public holiday]", "za"));
targetUnits.add(new Unit(30012, "Heritage Day [public holiday]", "za"));
targetUnits.add(new Unit(30013, "Ascension Day [public holiday]", "za", false));
targetUnits.add(new Unit(30014, "Empire Day [public holiday]", "za", false));
targetUnits.add(new Unit(30015, "Union Day [public holiday]", "za", false));
targetUnits.add(new Unit(30016, "Republic Day [public holiday]", "za", false));
targetUnits.add(new Unit(30017, "Queen's Birthday [public holiday]", "za", false));
targetUnits.add(new Unit(30018, "King's Birthday [public holiday]", "za", false));
targetUnits.add(new Unit(30019, "Settlers' Day [public holiday]", "za", false));
targetUnits.add(new Unit(30020, "Kruger Day [public holiday]", "za", false));
targetUnits.add(new Unit(30021, "Dingaan's Day [public holiday]", "za", false));
targetUnits.add(new Unit(30022, "Day of the Covenant [public holiday]", "za", false));
targetUnits.add(new Unit(30023, "Day of the Vow [public holiday]", "za", false));
targetUnits.add(new Unit(30024, "Day of Reconciliation [public holiday]", "za"));
targetUnits.add(new Unit(30025, "Christmas Day [public holiday]", "za"));
targetUnits.add(new Unit(30026, "Boxing Day [public holiday]", "za", false));
targetUnits.add(new Unit(30027, "Day of Goodwill [public holiday]", "za"));
targetUnits.add(new Unit(30028, "Begin Daylight Saving Time (DST)", "za"));
targetUnits.add(new Unit(30029, "End Daylight Saving Time (DST)", "za"));

// cn
targetUnits.add(new Unit(31000, "\u5143\u65e6 [New Year's Day; public]", "cn"));
targetUnits.add(new Unit(31001, "\u6625\u8282 (Eve) [Spring Festival (Chinese New Year's Eve); public]", "cn", false));
targetUnits.add(new Unit(31002, "\u6625\u8282 (1st day) [Spring Festival (Chinese New Year, 1st day); public]", "cn"));
targetUnits.add(new Unit(31003, "\u6625\u8282 (2nd day) [Spring Festival (Chinese New Year, 2nd day); public]", "cn"));
targetUnits.add(new Unit(31004, "\u6625\u8282 (3rd day) [Spring Festival (Chinese New Year, 3rd day); public]", "cn"));
targetUnits.add(new Unit(31005, "\u6e05\u660e\u8282 [Tomb-Sweeping Day (Qingming Festival); public]", "cn"));
targetUnits.add(new Unit(31006, "\u6e05\u660e\u8282 [Labour Day; public]", "cn"));
targetUnits.add(new Unit(31007, "\u6e05\u660e\u8282 (2nd day) [Labour Day (2nd day); public]", "cn"));
targetUnits.add(new Unit(31008, "\u6e05\u660e\u8282 (3rd day) [Labour Day (3rd day); public]", "cn"));
targetUnits.add(new Unit(31009, "\u7aef\u5348\u8282 [Dragon Boat Festival; public]", "cn"));
targetUnits.add(new Unit(31010, "\u4e2d\u79cb\u8282 [Mid-Autumn Festival; public]", "cn"));
targetUnits.add(new Unit(31011, "\u56fd\u5e86\u8282 [National Day (1st day); public]", "cn"));
targetUnits.add(new Unit(31012, "\u56fd\u5e86\u8282 (2nd day) [National Day (2nd day); public]", "cn"));
targetUnits.add(new Unit(31013, "\u56fd\u5e86\u8282 (3rd day) [National Day (3rd day); public]", "cn"));
targetUnits.add(new Unit(31014, "\u5143\u65e6 [New Year's Day; traditional]", "cn"));
targetUnits.add(new Unit(31015, "\u6625\u8282 [Chinese New Year's Eve; traditional]", "cn"));
targetUnits.add(new Unit(31016, "\u6625\u8282 [Chinese New Year (Spring Festival); traditional]", "cn"));
targetUnits.add(new Unit(31017, "\u5143\u5bb5\u8282 [Lantern Festival; traditional]", "cn"));
targetUnits.add(new Unit(31018, "\u4e2d\u548c\u8282 [Zhonghe Festival; traditional]", "cn"));
targetUnits.add(new Unit(31019, "\u4e0a\u5df3\u8282 [Double Third Festival (Shangsi Festival); traditional]", "cn"));
targetUnits.add(new Unit(31020, "\u56fd\u9645\u5987\u5973\u8282 [International Women's Day; traditional; public holiday applicable to women (half-day)]", "cn"));
targetUnits.add(new Unit(31021, "\u690d\u6811\u8282 [Arbor Day; traditional]", "cn"));
targetUnits.add(new Unit(31022, "\u6e05\u660e\u8282 [Tomb-Sweeping Day (Qingming Festival); traditional]", "cn"));
targetUnits.add(new Unit(31023, "\u52b3\u52a8\u8282 [Labour Day; traditional]", "cn"));
targetUnits.add(new Unit(31024, "\u9752\u5e74\u8282 [Youth Day; traditional; public holiday applicable to youth from the age of 14 to 28 (half-day)]", "cn"));
targetUnits.add(new Unit(31025, "\u516d\u4e00\u513f\u7ae5\u8282 [Children's Day; traditional; public holiday applicable to children below the age of 14 (1 day)]", "cn"));
targetUnits.add(new Unit(31026, "\u7aef\u5348\u8282 [Dragon Boat Festival; traditional]", "cn"));
targetUnits.add(new Unit(31027, "\u5efa\u515a\u8282 [CPC Founding Day; traditional]", "cn"));
targetUnits.add(new Unit(31028, "\u4e2d\u56fd\u822a\u6d77\u65e5 [China National Maritime Day; traditional]", "cn"));
targetUnits.add(new Unit(31029, "\u5efa\u519b\u8282 [Army Day; traditional; public holiday for military personnel in active service (half-day)]", "cn"));
targetUnits.add(new Unit(31030, "\u4e03\u5915 [Double Seven Festival (Qixi Festival); traditional]", "cn"));
targetUnits.add(new Unit(31031, "\u4e2d\u5143\u8282 [Spirit Festival (Ghost Festival); traditional]", "cn"));
targetUnits.add(new Unit(31032, "\u4e2d\u79cb\u8282 [Mid-Autumn Festival (Moon Festival); traditional]", "cn"));
targetUnits.add(new Unit(31033, "\u4e2d\u56fd\u4eba\u6c11\u6297\u65e5\u6218\u4e89\u80dc\u5229\u7eaa\u5ff5\u65e5 [Victory over Japan Day (V-J Day); traditional]", "cn"));
targetUnits.add(new Unit(31034, "\u70c8\u58eb\u7eaa\u5ff5\u65e5 [National Memorial Day; traditional]", "cn"));
targetUnits.add(new Unit(31035, "\u56fd\u5e86\u8282 [National Day; traditional]", "cn"));
targetUnits.add(new Unit(31036, "\u91cd\u967d\u7bc0 / \u91cd\u9633\u8282 [Double Ninth Festival (Chongyang Festival); traditional]", "cn"));
targetUnits.add(new Unit(31037, "\u4e0b\u5143\u8282 [Spirit Festival (Water Lantern Festival); traditional]", "cn"));
targetUnits.add(new Unit(31038, "\u814a\u516b\u8282 [Laba Festival; traditional]", "cn"));
targetUnits.add(new Unit(31039, "\u5357\u4eac\u5927\u5c60\u6740\u6b7b\u96be\u8005\u56fd\u5bb6\u516c\u796d\u65e5 [Nanking Massacre Memorial Day; traditional]", "cn"));
targetUnits.add(new Unit(31040, "[March Equinox]", "cn"));
targetUnits.add(new Unit(31041, "[September Equinox]", "cn"));
targetUnits.add(new Unit(31042, "[June Solstice]", "cn"));
targetUnits.add(new Unit(31043, "[December Solstice]", "cn"));

// tw
targetUnits.add(new Unit(32000, "\u5143\u65e6 [New Year's Day; public]", "tw"));
targetUnits.add(new Unit(32001, "\u8fb2\u66c6\u9664\u5915 [Lunar New Year's Eve; public]", "tw"));
targetUnits.add(new Unit(32002, "\u6625\u8282 (1st day) [Spring Festival (Lunar New Year, 1st day); public]", "tw"));
targetUnits.add(new Unit(32003, "\u6625\u8282 (2nd day) [Spring Festival (Lunar New Year, 2nd day); public]", "tw"));
targetUnits.add(new Unit(32004, "\u6625\u8282 (3rd day) [Spring Festival (Lunar New Year, 3rd day); public]", "tw"));
targetUnits.add(new Unit(32005, "228\u548c\u5e73\u7d00\u5ff5\u65e5 [Peace Memorial Day; public]", "tw"));
targetUnits.add(new Unit(32006, "\u5a66\u5973\u7bc0\u3001\u5152\u7ae5\u7bc0\u5408\u5002 [The Combined Holidays of Women's Day and Children's Day; public]", "tw"));
targetUnits.add(new Unit(32007, "\u6e05\u660e\u8282 [Tomb-Sweeping Day (Qingming Festival); public]", "tw"));
targetUnits.add(new Unit(32008, "\u7aef\u5348\u8282 [Dragon Boat Festival; public]", "tw"));
targetUnits.add(new Unit(32009, "\u4e2d\u79cb\u8282 [Mid-Autumn Festival; public]", "tw"));
targetUnits.add(new Unit(32010, "\u96d9\u5341\u7bc0 [National Day/Double Tenth Day; public]", "tw"));
targetUnits.add(new Unit(32011, "[March Equinox]", "tw"));
targetUnits.add(new Unit(32012, "[June Solstice]", "tw"));
targetUnits.add(new Unit(32013, "[September Equinox]", "tw"));
targetUnits.add(new Unit(32014, "[December Solstice]", "tw"));
targetUnits.add(new Unit(32015, "[Begin Daylight Saving Time (DST)]", "tw", false));
targetUnits.add(new Unit(32016, "[End Daylight Saving Time (DST)]", "tw", false));

// jp
targetUnits.add(new Unit(33000, "Ganjitsu [New Year's Day; national holiday]", "jp"));
targetUnits.add(new Unit(33001, "Seijin no hi [Coming of Age Day; national holiday]", "jp"));
targetUnits.add(new Unit(33002, "setsubun [Setsubun]", "jp"));
targetUnits.add(new Unit(33003, "Kigen-setsu", "jp", false));
targetUnits.add(new Unit(33004, "Kenkoku kinen no hi [National Foundation Day; national holiday]", "jp"));
targetUnits.add(new Unit(33005, "[Valentine's Day]", "jp"));
targetUnits.add(new Unit(33006, "hina matsuri [Doll's Festival; national holiday]", "jp"));
targetUnits.add(new Unit(33007, "hina matsuri [White Day; national holiday]", "jp"));
targetUnits.add(new Unit(33008, "Shunki k\u014drei-sai", "jp"));
targetUnits.add(new Unit(33009, "Shunbun no hi [Vernal equinox; national holiday]", "jp"));
targetUnits.add(new Unit(33010, "Sh\u014dwa no hi [Sh\u014dwa Day; national holiday]", "jp"));
targetUnits.add(new Unit(33011, "Kenp\u014d kinenbi [Constitution Memorial Day; national holiday]", "jp"));
targetUnits.add(new Unit(33012, "Kokumin no ky\u016bjitsu", "jp", false));
targetUnits.add(new Unit(33013, "Midori no hi [Greenery Day; national holiday]", "jp"));
targetUnits.add(new Unit(33014, "Kodomo no hi [Children's Day; national holiday]", "jp"));
targetUnits.add(new Unit(33015, "Umi no hi [Marine Day; national holiday]", "jp"));
targetUnits.add(new Unit(33016, "Keir\u014d no hi [Respect-for-the-Aged Day; national holiday]", "jp"));
targetUnits.add(new Unit(33017, "Sh\u016bki k\u014drei-sai", "jp"));
targetUnits.add(new Unit(33018, "Sh\u016bbun no hi [Autumnal equinox; national holiday]", "jp"));
targetUnits.add(new Unit(33019, "Taiiku no hi [Health and Sports Day; national holiday]", "jp"));
targetUnits.add(new Unit(33020, "Meiji-setsu [Meiji-setsu; national holiday]", "jp"));
targetUnits.add(new Unit(33021, "Bunka no hi [Culture Day; national holiday]", "jp"));
targetUnits.add(new Unit(33022, "Niiname-sai [Niiname-sai; festival]", "jp", false));
targetUnits.add(new Unit(33023, "Kinr\u014d kansha no hi [Labor Thanksgiving Day; national holiday]", "jp"));
targetUnits.add(new Unit(33024, "Tench\u014dsetsu", "jp", false));
targetUnits.add(new Unit(33025, "Tenn\u014d tanj\u014dbi [The Emperor's Birthday; national holiday]", "jp"));
targetUnits.add(new Unit(33026, "[March Equinox; astronomical event]", "jp"));
targetUnits.add(new Unit(33027, "[June Solstice; astronomical event]", "jp"));
targetUnits.add(new Unit(33028, "[September Equinox; astronomical event]", "jp"));
targetUnits.add(new Unit(33029, "[December Solstice; astronomical event]", "jp"));
targetUnits.add(new Unit(33030, "Shunki k\u014dreisai [Vernal Imperial Rituals]", "jp", false));
targetUnits.add(new Unit(33031, "Shunbun no Hi [Vernal Equinox Day]", "jp"));
targetUnits.add(new Unit(33032, "Sh\u016bki k\u014drei-sai [Autumn Imperial Rituals]", "jp", false));
targetUnits.add(new Unit(33033, "Sh\u016bbun no Hi [Autumnal Equinox Day]", "jp"));
targetUnits.add(new Unit(33034, "[Begin Daylight Saving Time (DST)]", "jp", false));
targetUnits.add(new Unit(33035, "[End Daylight Saving Time (DST)]", "jp", false));

// au
targetUnits.add(new Unit(34000, "New Year [public holiday]", "au"));
targetUnits.add(new Unit(34001, "Australia Day [public holiday]", "au"));
targetUnits.add(new Unit(34002, "Royal Hobart Regatta [publich holiday, regional; Tasmania, Hobart area only]", "au"));
targetUnits.add(new Unit(34003, "Labour Day [publich holiday, regional; Western Australia only]", "au"));
targetUnits.add(new Unit(34004, "Adelaide Cup [publich holiday, regional; South Australia]", "au"));
targetUnits.add(new Unit(34005, "Canberra Day [publich holiday, regional; Australian Capital Territory]", "au"));
targetUnits.add(new Unit(34006, "Eight Hours Day [publich holiday, regional; Tasmania]", "au"));
targetUnits.add(new Unit(34007, "Labour Day [public holiday, regional; Victoria]", "au"));
targetUnits.add(new Unit(34008, "Good Friday [public holiday]", "au"));
targetUnits.add(new Unit(34009, "Easter Saturday (Holy Saturday) [public holiday]", "au"));
targetUnits.add(new Unit(34010, "Easter Sunday [public holiday]", "au"));
targetUnits.add(new Unit(34011, "Easter Monday [public holiday]", "au"));
targetUnits.add(new Unit(34012, "Easter Monday [public holiday, regional; Tasmania]", "au"));
targetUnits.add(new Unit(34013, "Anzac Day [public holiday]", "au"));
targetUnits.add(new Unit(34014, "Labour Day [public holiday, regional; Queensland only]", "au"));
targetUnits.add(new Unit(34015, "May Day [publich holiday, regional; Northern Territory only]", "au"));
targetUnits.add(new Unit(34016, "Mother's Day", "au"));
targetUnits.add(new Unit(34017, "Foundation Day [publich holiday, regional; Western Australia only]", "au"));
targetUnits.add(new Unit(34018, "Queen's Birthday [publich holiday, regional; all exept Western Australia]", "au"));
targetUnits.add(new Unit(34019, "Picnic Day [publich holiday, regional; Northern Territory]", "au"));
targetUnits.add(new Unit(34020, "Labour Day [public holiday, regional; ACT, NSW, SA]", "au"));
targetUnits.add(new Unit(34021, "Recreation Day [public holiday, regional; Tasmania, Not Hobart area]", "au"));
targetUnits.add(new Unit(34022, "Family & Community Day [public holiday, regional; ACT]", "au"));
targetUnits.add(new Unit(34023, "Melbourne Cup Day [publich holiday, regional; Victoria only]", "au"));
targetUnits.add(new Unit(34024, "Christmas Day [public holiday]", "au"));
targetUnits.add(new Unit(34025, "Boxing Day [public holiday; all, except South Australia]", "au"));
targetUnits.add(new Unit(34026, "Proclamation Day [public holiday; South Australia only]", "au"));

// nz
targetUnits.add(new Unit(35000, "New Year's Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35001, "Day after New Year's Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35002, "Waitangi Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35003, "Wellington Anniversary Day [Provincial Anniversary Day; Wellington Province]", "nz"));
targetUnits.add(new Unit(35004, "Auckland Anniversary Day [Provincial Anniversary Day; Auckland Province]", "nz"));
targetUnits.add(new Unit(35005, "Northland Anniversary Day [Provincial Anniversary Day; Northland]", "nz"));
targetUnits.add(new Unit(35006, "Nelson Anniversary Day [Provincial Anniversary Day; Nelson]", "nz"));
targetUnits.add(new Unit(35007, "Good Friday [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35008, "Easter Sunday", "nz"));
targetUnits.add(new Unit(35009, "Easter Monday [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35010, "Otago Anniversary Day [Provincial Anniversary Day; Otago Province]", "nz"));
targetUnits.add(new Unit(35011, "Taranaki Anniversary Day [Provincial Anniversary Day; New Plymouth]", "nz"));
targetUnits.add(new Unit(35012, "Anzac Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35013, "Queen's Birthday [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35014, "South Canterbury Anniversary Day [Provincial Anniversary Day; South Canterbury]", "nz"));
targetUnits.add(new Unit(35015, "Hawke's Bay Anniversary Day [Provincial Anniversary Day; Hawke's Bay]", "nz"));
targetUnits.add(new Unit(35016, "Labour Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35017, "Marlborough Anniversary Day [Provincial Anniversary Day; Marlborough]", "nz"));
targetUnits.add(new Unit(35018, "Christchurch Show Day [Provincial Anniversary Day; Northern and Central Canterbury]", "nz"));
targetUnits.add(new Unit(35019, "Canterbury Anniversary Day [Provincial Anniversary Day; Christchurch City]", "nz"));
targetUnits.add(new Unit(35020, "Chatham Islands Anniversary Day [Provincial Anniversary Day; Chatham Islands]", "nz"));
targetUnits.add(new Unit(35021, "Westland Anniversary Day [Provincial Anniversary Day; Greymouth]", "nz"));
targetUnits.add(new Unit(35022, "Christmas Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35023, "Boxing Day [Statutory holiday]", "nz"));
targetUnits.add(new Unit(35024, "Begin Daylight Saving Time (DST)", "nz"));
targetUnits.add(new Unit(35025, "End Daylight Saving Time (DST)", "nz"));

// ddr
targetUnits.add(new Unit(36000, "Neujahr [New Year's Day; public holiday]", "ddr", false));
targetUnits.add(new Unit(36001, "Karfreitag [Good Friday; public holiday]", "ddr", false));
targetUnits.add(new Unit(36002, "Ostersonntag (Ostern) [Easter Sunday]", "ddr", false));
targetUnits.add(new Unit(36003, "Ostermontag", "ddr", false));
targetUnits.add(new Unit(36004, "Internationaler Kampf- und Feiertag der Werkt\u00e4tigen f\u00fcr Frieden und Sozialismus", "ddr", false));
targetUnits.add(new Unit(36005, "Tag der Befreiung", "ddr", false));
targetUnits.add(new Unit(36006, "Tag des Sieges", "ddr", false));
targetUnits.add(new Unit(36007, "Christi Himmelfahrt", "ddr", false));
targetUnits.add(new Unit(36008, "Pfingstsonntag (Pfingsten)", "ddr", false));
targetUnits.add(new Unit(36009, "Pfingstmontag", "ddr", false));
targetUnits.add(new Unit(36010, "Tag der Republik", "ddr", false));
targetUnits.add(new Unit(36011, "Reformationstag", "ddr", false));
targetUnits.add(new Unit(36012, "Bu\u00df- und Bettag [Day of Repentance and Prayer; public holiday]", "ddr", false));
targetUnits.add(new Unit(36013, "1. Weihnachtsfeiertag (Weihnachten) [Christmas Day; public holiday]", "ddr", false));
targetUnits.add(new Unit(36014, "2. Weihnachtsfeiertag (Weihnachten) [Boxing Day; public holiday]", "ddr", false));

		
		defaultTargetUnit = targetUnits.get(0);
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return sourceUnits;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return targetUnits;
    }

    public int getSourceDefault() {
        return 0;
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }
	
    public int getTargetDefault() {
        return 0;
    }
	
    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    public boolean isOneway() {
        return true;
    }

    public void setNumberType(int numberType) {
        switch (numberType) {
            case 0: df = DateFormat.getDateInstance(DateFormat.SHORT);
                    break;
            case 1: df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                    break;
            case 2: df = DateFormat.getDateInstance(DateFormat.LONG);
                    break;
            case 3: df = DateFormat.getDateInstance(DateFormat.FULL);
                    break;
            default: df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                     break;
        }
    }
	
    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = sourceUnits.get(s);        
        Unit targetUnit = targetUnits.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();

        ExtendedGregorianCalendar gc = new ExtendedGregorianCalendar();
        boolean defined = false;
        int year = 0;

        switch (sid) {
            // international
            case cYEAR:   try {
                            year = Integer.parseInt(input);
                            if ((year <=0 ) || (year > 9999)) throw new Exception(INVALID);
                          } catch (Exception e) { throw new Exception(INVALID); }
                          break;
        }

        switch (tid) {

            // int
// World Leprosy Day
case 0:
if (year >= 1954) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.JANUARY,
    year);

}
break;

// World Braille Day
case 1:
if (year >= 2001) {
defined = true;
gc.set(year, Calendar.JANUARY, 4);

}
break;

// National Hugging Day
case 2:
if (year >= 1986) {
defined = true;
gc.set(year, Calendar.JANUARY, 21);

}
break;

// Change Your Password Day
case 3:
if (year >= 2016) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 1);

}
break;

// World Cancer Day
case 4:
if (year >= 2000) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 4);

}
break;

// Safer Internet Day
case 5:
if (year >= 2004) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);
gc.add(Calendar.DATE, -5);

}
break;

// International Ear Care Day
case 6:
if (year >= 2007 && year <= 2015) {
defined = true;
gc.set(year, Calendar.MARCH, 3);

}
break;

// World Hearing Day
case 7:
if (year >= 2015) {
defined = true;
gc.set(year, Calendar.MARCH, 3);

}
break;

// World Meteorological day
case 8:
if (year >= 1950) {
defined = true;
gc.set(year, Calendar.MARCH, 23);

}
break;

// Pi Day
case 9:
if (year >= 1988) {
defined = true;
gc.set(year, Calendar.MARCH, 14);

}
break;

// Earth Day
case 10:
if (year >= 1970) {
defined = true;
gc.set(year, Calendar.APRIL, 22);

}
break;

// World Password Day
case 11:
if (year >= 2013) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.THURSDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Towel Day
case 12:
if (year <= 2000) {
defined = true;
return NOT_DEFINED;
}
if (year >= 2001) {
defined = true;
gc.set(year, Calendar.MAY, 25);

}
break;

// International Kissing Day
case 13:
defined = true;
gc.set(year, Calendar.JULY, 6);
break;

// World Chocolate Day
case 14:
if (year >= 2009) {
defined = true;
gc.set(year, Calendar.JULY, 7);

}
break;

// International Beer Day
case 15:
if (year >= 2007 && year <= 2012) {
defined = true;
gc.set(year, Calendar.AUGUST, 5);

}
if (year >= 2013) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.FRIDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);

}
break;

// World Animal Day
case 16:
if (year >= 1931) {
defined = true;
gc.set(year, Calendar.OCTOBER, 4);

}
break;

// International Tea Day
case 17:
if (year >= 2005) {
defined = true;
gc.set(year, Calendar.DECEMBER, 15);

}
break;

// uno
// International Holocaust Remembrance Day
case 1000:
if (year >= 2005) {
defined = true;
gc.set(year, Calendar.JANUARY, 27);

}
break;

// World Cancer Day
case 1001:
if (year >= 2010) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 4);

}
break;

// International Women's Day
case 1002:
if (year >= 1978) {
defined = true;
gc.set(year, Calendar.MARCH, 8);

}
break;

// World Water Day
case 1003:
if (year >= 1993) {
defined = true;
gc.set(year, Calendar.MARCH, 22);

}
break;

// World Health Day
case 1004:
if (year >= 1948) {
defined = true;
gc.set(year, Calendar.APRIL, 7);

}
break;

// World Book and Copyright Day
case 1005:
if (year >= 1995) {
defined = true;
gc.set(year, Calendar.APRIL, 23);

}
break;

// World Press Freedom Day
case 1006:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.MAY, 3);

}
break;

// International Day for Biological Diversity
case 1007:
if (year >= 1993 && year <= 2000) {
defined = true;
gc.set(year, Calendar.DECEMBER, 29);

}
if (year >= 2001) {
defined = true;
gc.set(year, Calendar.MAY, 22);

}
break;

// World Environment Day
case 1008:
if (year >= 1972) {
defined = true;
gc.set(year, Calendar.JUNE, 5);

}
break;

// International Day of Cooperatives
case 1009:
if (year >= 1995) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.JULY,
    year);

}
break;

// World Statistics Day
case 1010:
if (year >= 2010 && (year - 2010)%5 == 0) {
defined = true;
gc.set(year, Calendar.OCTOBER, 20);

}
break;

// United Nations Day
case 1011:
if (year >= 1947) {
defined = true;
gc.set(year, Calendar.OCTOBER, 24);

}
break;

// Universal Children's Day
case 1012:
if (year >= 1954) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 20);

}
break;

// World Toilet Day
case 1013:
if (year >= 2013) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 19);

}
break;

// Africa Industrialization Day
case 1014:
if (year >= 1989) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 20);

}
break;

// World AIDS Day
case 1015:
if (year >= 1988) {
defined = true;
gc.set(year, Calendar.DECEMBER, 1);

}
break;

// Human Rights Day
case 1016:
if (year >= 1950) {
defined = true;
gc.set(year, Calendar.DECEMBER, 10);

}
break;

// us
// New Year's Day [federal holiday]
case 2000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Three Kings Day
case 2001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Martin Luther King, Jr's Birthday [actual birthday]
case 2002:
if (year >= 1929) {
defined = true;
gc.set(year, Calendar.JANUARY, 15);

}
break;

// Martin Luther King, Jr. Day [observed, national]
case 2003:
if (year >= 1986) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JANUARY,
    year);

}
break;

// Inauguration Day
case 2004:
if (year == 1789) {
defined = true;
gc.set(year, Calendar.APRIL, 30);

}
if (year >= 1793 && year <= 1936 && (year - 1793)%4 == 0) {
defined = true;
gc.set(year, Calendar.MARCH, 4);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
if (year >= 1937 && (year - 1937)%4 == 0) {
defined = true;
gc.set(year, Calendar.JANUARY, 20);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Groundhog Day [cultural]
case 2005:
if (year >= 1777) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 2);

}
break;

// Mardi Gras (Fat Tuesday) [cultural, christian; New Orleans, Louisiana]
case 2006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// Ash Wednesday
case 2007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
break;

// Laetare Sunday (Mothering Sunday, Mid-Lent Sunday, Rose Sunday)
case 2008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -21);
break;

// Lincoln's Birthday [local]
case 2009:
if (year >= 1865 && year <= 1971) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 12);

}
break;

// Valentine's Day
case 2010:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// Washington's Birthday (President's Day) [federal holiday]
case 2011:
if (year >= 1885 && year <= 1970) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 22);

}
if (year >= 1971) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);

}
break;

// National Teacher Day
case 2012:
if (year >= 1980 && year <= 1980) {
defined = true;
gc.set(year, Calendar.MARCH, 7);

}
if (year >= 1981 && year <= 1985) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.TUESDAY,
    ExtendedGregorianCalendar.MARCH,
    year);

}
if (year >= 1986) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
gc.add(Calendar.DATE, 2);

}
break;

// Saint Patrick's Day [Christian, Festive]
case 2013:
if (year >= 1737) {
defined = true;
gc.set(year, Calendar.MARCH, 17);

}
break;

// Saint Joseph's Day [Christian, Catholic]
case 2014:
if (year >= 1777) {
defined = true;
gc.set(year, Calendar.MARCH, 19);

}
break;

// April Fools' Day
case 2015:
defined = true;
gc.set(year, Calendar.APRIL, 1);
break;

// Daylight Saving Time Begins (DST), +1h
case 2016:
if (year >= 1777) {
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("America/New_York"));
} catch (Exception e) {
    return NOT_DEFINED;
}

}
break;

// Tax Day
case 2017:
if (year <= 1776) {
defined = true;
return NOT_DEFINED;
}
if (year >= 1777) {
defined = true;
gc.set(year, Calendar.APRIL, 15);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Earth Day
case 2018:
if (year <= 1969) {
defined = true;
return NOT_DEFINED;
}
if (year >= 1970) {
defined = true;
gc.set(year, Calendar.APRIL, 22);

}
break;

// National Secretaries Day
case 2019:
if (year >= 1952 && year <= 1954) {
defined = true;
gc.set(year, Calendar.JUNE, 4);

}
if (year >= 1955 && year <= 1980) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.APRIL,
    year);
gc.add(Calendar.DATE, -3);

}
break;

// Professional Secretaries Day
case 2020:
if (year >= 1981 && year <= 1999) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.APRIL,
    year);
gc.add(Calendar.DATE, -3);

}
break;

// Administrative Professionals Day
case 2021:
if (year >= 2000) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.APRIL,
    year);
gc.add(Calendar.DATE, -3);

}
break;

// Palm Sunday
case 2022:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -7);
break;

// Good Friday
case 2023:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Holy Saturday (Easter Even)
case 2024:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -1);
break;

// Easter Day
case 2025:
defined = true;
gc.setEaster(year,false);
break;

// Arbor Day [cultural]
case 2026:
if (year >= 1872) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.FRIDAY,
    ExtendedGregorianCalendar.APRIL,
    year);

}
break;

// Cinco de Mayo [Mexican American]
case 2027:
if (year >= 1862) {
defined = true;
gc.set(year, Calendar.MAY, 5);

}
break;

// Mother's Day
case 2028:
if (year >= 1912) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Memorial Day [federal holiday]
case 2029:
if (year >= 1868 && year <= 1970) {
defined = true;
gc.set(year, Calendar.MAY, 30);

}
if (year >= 1971) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Flag Day
case 2030:
if (year >= 1916) {
defined = true;
gc.set(year, Calendar.JUNE, 14);

}
break;

// Father's Day
case 2031:
if (year >= 1972) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.JUNE,
    year);

}
break;

// Independence Day (The Fourth of July) [federal holiday]
case 2032:
if (year >= 1776) {
defined = true;
gc.set(year, Calendar.JULY, 4);

}
break;

// Independence Day (observed) [federal holiday]
case 2033:
if (year >= 1776) {
defined = true;
gc.set(year, Calendar.JULY, 4);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, -1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Labor Day [federal holiday]
case 2034:
if (year >= 1894) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Patriot Day
case 2035:
if (year >= 2002) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 11);

}
break;

// Patriot's Day [Massachusetts and state of Maine]
case 2036:
if (year >= 1775 && year <= 1968) {
defined = true;
gc.set(year, Calendar.APRIL, 19);

}
if (year >= 1969) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.APRIL,
    year);

}
break;

// Grandparents Day [national]
case 2037:
if (year >= 1978) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
gc.add(Calendar.DATE, 6);

}
break;

// Columbus Day [federal holiday]
case 2038:
if (year >= 1792) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
break;

// Boss' Day (National Boss Day, Bosses Day)
case 2039:
if (year >= 1962) {
defined = true;
gc.set(year, Calendar.OCTOBER, 16);

}
break;

// Daylight Saving Time Ends (DST), -1h
case 2040:
if (year >= 1776) {
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("America/New_York"));
} catch (Exception e) {
    return NOT_DEFINED;
}

}
break;

// Halloween
case 2041:
if (year >= 1776) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
break;

// All Saint's Day [Western Churches]
case 2042:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// All Soul's Day
case 2043:
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);
break;

// Election Day
case 2044:
if (year >= 1845) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
gc.add(Calendar.DATE, 1);

}
break;

// Election of President and Vice President of the United States
case 2045:
if (year >= 1788) {
defined = true;
gc.set(year, Calendar.DECEMBER, 15);

}
if (year >= 1792) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);

}
if (year >= 1796) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 4);

}
if (year >= 1800) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
if (year >= 1804) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);

}
if (year >= 1808) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 4);

}
if (year >= 1812) {
defined = true;
gc.set(year, Calendar.OCTOBER, 30);

}
if (year >= 1816) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);

}
if (year >= 1820) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);

}
if (year >= 1824) {
defined = true;
gc.set(year, Calendar.OCTOBER, 26);

}
if (year >= 1828) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
if (year >= 1832) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);

}
if (year >= 1836) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 3);

}
if (year >= 1840) {
defined = true;
gc.set(year, Calendar.OCTOBER, 30);

}
if (year >= 1844) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);

}
if (year >= 1848 && (year - 1848)%4 == 0) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
gc.add(Calendar.DATE, 1);

}
break;

// Midterm elections
case 2046:
if (year >= 1910 && (year - 1910)%4 == 0) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
gc.add(Calendar.DATE, 1);

}
break;

// Armistice's Day [federal holiday]
case 2047:
if (year >= 1938 && year <= 1953) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);

}
break;

// Veterans Day [federal holiday]
case 2048:
if (year >= 1954) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);

}
break;

// Thanksgiving [federal holiday]
case 2049:
if (year >= 1776) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.THURSDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);

}
break;

// Black Friday (Friday after Thanksgiving) [non federal holiday]
case 2050:
if (year >= 1776) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.THURSDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
gc.add(Calendar.DATE, 1);

}
break;

// Christmas Eve
case 2051:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Christmas Day [federal holiday]
case 2052:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Kwanzaa (7 days) [African American]
case 2053:
if (year >= 1966) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);

}
break;

// New Year's Eve
case 2054:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// First Day of Spring
case 2055:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// First Day of Summer
case 2056:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// First Day of Autumn
case 2057:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// First Day of Winter
case 2058:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// ca
// New Year's Day / Le jour de l'An [nationwide statutory holiday]
case 3000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Valentine's Day / Saint-Valentin
case 3001:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// National Flag of Canada Day
case 3002:
if (year >= 1965) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 15);

}
break;

// Family Day / F\u00eate de la famille [common statutory holiday; Alberta, Ontario, Saskatchewan]
case 3003:
if (year >= 1990) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);

}
break;

// Louis Riel Day [public holiday; Canadian province of Manitoba]
case 3004:
if (year >= 2008) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);

}
break;

// Caribana
case 3005:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);
break;

// Shrove Monday (Rose Monday)
case 3006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -48);
break;

// Strove Thuesday (Pancake Day, Pancake Tuesday)
case 3007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// Ash Wednesday
case 3008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
break;

// Daylight Saving Time starts (change time 1 h forward)
case 3009:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Canada/Central"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Good Friday / Le vendredi saint [nationwide statutory holiday]
case 3010:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Holy Saturday
case 3011:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -1);
break;

// Easter Sunday / P\u00e2ques
case 3012:
defined = true;
gc.setEaster(year,false);
break;

// Easter Monday / Lundi de P\u00e2ques [statutory holiday]
case 3013:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Mother's Day / F\u00eate des M\u00e8res
case 3014:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// Victoria Day / La f\u00eate de la Reine [statutory holiday]
case 3015:
if (year >= 1901 && year <= 1952) {
defined = true;
gc.set(year, Calendar.MAY, 24);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
if (year >= 1953) {
defined = true;
gc.set(year, Calendar.MAY, 25);
gc.setPreviousWeekday(Calendar.MONDAY,true);

}
break;

// Canada Day / La f\u00eate du Canada [nationwide statutory holiday]
case 3016:
if (year >= 1867) {
defined = true;
gc.set(year, Calendar.JULY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// August Civic Holiday / Premier lundi d'ao\u00fbt [local]
case 3017:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);
break;

// Discovery Day [statutory holiday; Yukon]
case 3018:
if (year >= 1896) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);

}
break;

// Discovery Day [Newfoundland and Labrador]
case 3019:
if (year >= 1497) {
defined = true;
gc.set(year, Calendar.JUNE, 24);
gc.setNearestWeekday(Calendar.MONDAY);

}
break;

// Nunavut Day [Nunavut]
case 3020:
defined = true;
gc.set(year, Calendar.JULY, 9);
break;

// National Aboriginal Day [Northwest Territories]
case 3021:
if (year >= 1996) {
defined = true;
gc.set(year, Calendar.JUNE, 21);

}
break;

// Memorial Day [Newfoundland and Labrador]
case 3022:
defined = true;
gc.set(year, Calendar.JULY, 1);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Orangemen's Day  [Newfoundland and Labrador]
case 3023:
if (year >= 1690) {
defined = true;
gc.set(year, Calendar.JULY, 12);
gc.setNearestWeekday(Calendar.MONDAY);

}
break;

// St. Patrick's Day [public holiday; Newfoundland and Labrador]
case 3024:
defined = true;
gc.set(year, Calendar.MARCH, 17);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// St. George's Day [Newfoundland and Labrador]
case 3025:
defined = true;
gc.set(year, Calendar.APRIL, 23);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Labour Day / La f\u00eate du travail [nationwide statutory holiday]
case 3026:
if (year >= 1872) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Thanksgiving / L'Action de gr\u00e2ce [statutory holiday, cultural]
case 3027:
if (year <= 1971) {
defined = true;
return UNKNOWN;

}
if (year >= 1792 && year <= 1792) {
defined = true;
gc.set(year, Calendar.APRIL, 15);

}
if (year >= 1879 && year <= 1879) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 6);

}
if (year >= 1880 && year <= 1880) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 3);

}
if (year >= 1881 && year <= 1881) {
defined = true;
gc.set(year, Calendar.OCTOBER, 20);

}
if (year >= 1882 && year <= 1882) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 9);

}
if (year >= 1883 && year <= 1883) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 8);

}
if (year >= 1884 && year <= 1884) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 6);

}
if (year >= 1885 && year <= 1885) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 12);

}
if (year >= 1886 && year <= 1886) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 18);

}
if (year >= 1887 && year <= 1887) {
defined = true;
gc.set(year, Calendar.JUNE, 21);

}
if (year >= 1887 && year <= 1887) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 17);

}
if (year >= 1888 && year <= 1888) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 15);

}
if (year >= 1889 && year <= 1889) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 7);

}
if (year >= 1890 && year <= 1890) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 6);

}
if (year >= 1891 && year <= 1891) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 12);

}
if (year >= 1892 && year <= 1892) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 10);

}
if (year >= 1893 && year <= 1893) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 23);

}
if (year >= 1894 && year <= 1894) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 22);

}
if (year >= 1895 && year <= 1895) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 21);

}
if (year >= 1896 && year <= 1896) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 26);

}
if (year >= 1896 && year <= 1896) {
defined = true;
gc.set(year, Calendar.JUNE, 22);

}
if (year >= 1897 && year <= 1897) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 25);

}
if (year >= 1898 && year <= 1898) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 24);

}
if (year >= 1899 && year <= 1899) {
defined = true;
gc.set(year, Calendar.OCTOBER, 19);

}
if (year >= 1900 && year <= 1900) {
defined = true;
gc.set(year, Calendar.OCTOBER, 18);

}
if (year >= 1901 && year <= 1901) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 28);

}
if (year >= 1902 && year <= 1902) {
defined = true;
gc.set(year, Calendar.JUNE, 26);

}
if (year >= 1902 && year <= 1902) {
defined = true;
gc.set(year, Calendar.AUGUST, 9);

}
if (year >= 1902 && year <= 1902) {
defined = true;
gc.set(year, Calendar.OCTOBER, 16);

}
if (year >= 1903 && year <= 1903) {
defined = true;
gc.set(year, Calendar.OCTOBER, 15);

}
if (year >= 1904 && year <= 1904) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 17);

}
if (year >= 1905 && year <= 1905) {
defined = true;
gc.set(year, Calendar.OCTOBER, 26);

}
if (year >= 1906 && year <= 1906) {
defined = true;
gc.set(year, Calendar.OCTOBER, 18);

}
if (year >= 1907 && year <= 1907) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
if (year >= 1908 && year <= 1908) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 9);

}
if (year >= 1909 && year <= 1912) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1913 && year <= 1913) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1914 && year <= 1919) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1920 && year <= 1920) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1921 && year <= 1930) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);
gc.setPreviousWeekday(Calendar.MONDAY,true);

}
if (year >= 1931 && year <= 1934) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1935 && year <= 1935) {
defined = true;
gc.set(year, Calendar.OCTOBER, 24);

}
if (year >= 1936) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
break;

// Halloween
case 3028:
defined = true;
gc.set(year, Calendar.OCTOBER, 31);
break;

// Daylight Saving Time ends (change time 1 h back)
case 3029:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Canada/Central"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Remembrance Day / Le jour du Souvenir [all except NB, NS, ON, QC]
case 3030:
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);
break;

// First Sunday Advent
case 3031:
defined = true;
gc.setAdvent(year);
break;

// Second Sunday Advent
case 3032:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 7);
break;

// Third Sunday Advent (Gaudete)
case 3033:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 14);
break;

// Fourth Sunday Advent
case 3034:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 21);
break;

// Christmas Eve / R\u00e9veillon de No\u00ebl
case 3035:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Christmas Day / No\u00ebl [nationwide statutory holiday]
case 3036:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Boxing Day / Le lendemain de No\u00ebl [statutory holiday]
case 3037:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// de
// Neujahrstag [New Year's Day; public holiday]
case 4000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Hl. Drei K\u00f6nige (Dreik\u00f6nigsfest) [Biblical Magi; public holiday; in BW,BY,ST]
case 4001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Internationaler Frauentag [International Women's Day; public holiday; in BE]
case 4002:
if (year >= 2019) {
defined = true;
gc.set(year, Calendar.MARCH, 8);

}
break;

// Erscheinung des Herrn (Epiphanie) [Epiphany; protestant]
case 4003:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Taufe des Herrn
case 4004:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
gc.setNextWeekday(Calendar.SUNDAY,true);
break;

// Tag des Gedenkens an die Opfer des Nationalsozialismus [The International Holocaust Remembrance Day; national]
case 4005:
if (year >= 1996) {
defined = true;
gc.set(year, Calendar.JANUARY, 27);

}
break;

// Darstellung des Herrn (Mari\u00e4 Lichtmess)
case 4006:
defined = true;
gc.set(year, Calendar.FEBRUARY, 2);
break;

// Valentinstag [Valentine's Day]
case 4007:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// Rosenmontag [Rose Monday]
case 4008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -48);
break;

// Fastnacht (Faschingsdienstag) [Carnival]
case 4009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// Aschermittwoch [Ash Wednesday]
case 4010:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
break;

// 1. Fastensonntag (Invocavit)
case 4011:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -42);
break;

// 2. Fastensonntag (Reminiscere)
case 4012:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -35);
break;

// 3. Fastensonntag (Oculi)
case 4013:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -28);
break;

// 4. Fastensonntag (L\u00e4tare, Rosensonntag, Brotsonntag, Todsonntag, Stabaus-Sonntag)
case 4014:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -21);
break;

// 5. Fastensonntag (Judica, Passionssonntag, Schwarzer Sonntag)
case 4015:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -14);
break;

// Beginn der Sommerzeit (Uhren 1 h vorstellen) [Begin Daylight Saving Time]
case 4016:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Berlin"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Dreifacher Hexensabbat, gro\u00dfer Verfallstag (1. Quartal) [Triple witching hour (1st quarter); stock market]
case 4017:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.FRIDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
if (false) { // daytype is not implemented 
gc.add(Calendar.DATE, -1);

}
break;

// Dreifacher Hexensabbat, gro\u00dfer Verfallstag (2. Quartal) [Triple witching hour (2nd quarter); stock market]
case 4018:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.FRIDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
if (false) { // daytype is not implemented 
gc.add(Calendar.DATE, -1);

}
break;

// Tag des offenen Denkmals [European Heritage Days; cultural]
case 4019:
if (year >= 1993) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Weltkindertag [International Children's Day; public holiday; TH]
case 4020:
if (year >= 2019) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 20);

}
break;

// Dreifacher Hexensabbat, gro\u00dfer Verfallstag (3. Quartal) [Triple witching hour (3rd quarter); stock market]
case 4021:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.FRIDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
if (false) { // daytype is not implemented 
gc.add(Calendar.DATE, -1);

}
break;

// Dreifacher Hexensabbat, gro\u00dfer Verfallstag (4. Quartal) [Triple witching hour (4th quarter); stock market]
case 4022:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.FRIDAY,
    ExtendedGregorianCalendar.DECEMBER,
    year);
if (false) { // daytype is not implemented 
gc.add(Calendar.DATE, -1);

}
break;

// Erster April [April Fool's Day]
case 4023:
if (year >= 1618) {
defined = true;
gc.set(year, Calendar.APRIL, 1);

}
break;

// Walpurgisnacht [Walpurgis Night]
case 4024:
defined = true;
gc.set(year, Calendar.APRIL, 30);
break;

// Palmsonntag (Palmarum) [Palm Sunday]
case 4025:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -7);
break;

// Krummer Mittwoch [Holy Wednesday]
case 4026:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -4);
break;

// Gr\u00fcndonnerstag [Maundy Thursday]
case 4027:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Karfreitag [Good Friday; public holiday]
case 4028:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Karsamstag [Holy Saturday]
case 4029:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -1);
break;

// Ostersonntag (Ostern) [Easter Sunday]
case 4030:
defined = true;
gc.setEaster(year,false);
break;

// Ostermontag (Emmaustag) [Easter Monday; public holiday]
case 4031:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Wei\u00dfer Sonntag (Barmherzigkeitssonntag) [Second Sunday of Easter; catholic]
case 4032:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 7);
break;

// Quasimodogeniti [First Sunday after Easter; protestant]
case 4033:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 7);
break;

// Maifeiertag (Tag der Arbeit) [Labour Day; public holiday]
case 4034:
if (year >= 1934) {
defined = true;
gc.set(year, Calendar.MAY, 1);

}
break;

// Muttertag [Mother's Day]
case 4035:
if (year >= 1923) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Kantate [Kantate; protestant]
case 4036:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 28);
break;

// Exaudi [Exaudi; protestant]
case 4037:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 42);
break;

// Christi Himmelfahrt [Ascension Day; public holiday]
case 4038:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pfingstsonntag (Pfingsten) [Pentecost]
case 4039:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Pfingstmontag [Whit Monday; public holiday]
case 4040:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// Eisheilige: Mamertus
case 4041:
defined = true;
gc.set(year, Calendar.MAY, 11);
break;

// Eisheilige: Pankratius
case 4042:
defined = true;
gc.set(year, Calendar.MAY, 12);
break;

// Eisheilige: Servatius
case 4043:
defined = true;
gc.set(year, Calendar.MAY, 13);
break;

// Eisheilige: Bonifatius
case 4044:
defined = true;
gc.set(year, Calendar.MAY, 14);
break;

// Eisheilige: Sophie
case 4045:
defined = true;
gc.set(year, Calendar.MAY, 15);
break;

// Nationaler Gedenktag
case 4046:
if (year >= 1953 && year <= 1990) {
defined = true;
gc.set(year, Calendar.JUNE, 17);

}
break;

// Fronleichnam [Corpus Christi; public holiday; BW,BY,HE,NW,RP,SL,SN partly,TH partly]
case 4047:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Friedensfest [Peace Festival; public holiday; only in Augsburg]
case 4048:
defined = true;
gc.set(year, Calendar.AUGUST, 8);
break;

// Mari\u00e4 Himmelfahrt [Assumption Day; catholic; SL,BY]
case 4049:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Oktoberfest (Er\u00f6ffnung) [Oktoberfest (Start); Festival; Munich]
case 4050:
if (year >= 1810 && year <= 1812) {
defined = true;
gc.set(year, Calendar.OCTOBER, 17);

}
if (year >= 1814 && year <= 1853) {
defined = true;
gc.set(year, Calendar.OCTOBER, 17);

}
if (year >= 1855 && year <= 1865) {
defined = true;
gc.set(year, Calendar.OCTOBER, 17);

}
if (year >= 1867 && year <= 1869) {
defined = true;
gc.set(year, Calendar.OCTOBER, 17);

}
if (year >= 1871 && year <= 1871) {
defined = true;
gc.set(year, Calendar.OCTOBER, 17);

}
if (year >= 1872 && year <= 1872) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);
gc.setNextWeekday(Calendar.SATURDAY,true);

}
if (year >= 1874 && year <= 1913) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);
gc.setNextWeekday(Calendar.SATURDAY,true);

}
if (year >= 1921 && year <= 1922) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);
gc.setNextWeekday(Calendar.SATURDAY,true);

}
if (year >= 1925 && year <= 1938) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);
gc.setNextWeekday(Calendar.SATURDAY,true);

}
if (year >= 1949) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);
gc.setNextWeekday(Calendar.SATURDAY,true);

}
break;

// Oktoberfest (Ende) [Oktoberfest (End); Festival; Munich]
case 4051:
if (year >= 1810 && year <= 1812) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1814 && year <= 1853) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1855 && year <= 1865) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1867 && year <= 1869) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1871 && year <= 1872) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1874 && year <= 1913) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1921 && year <= 1922) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1925 && year <= 1938) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1949 && year <= 1999) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 2000 && year <= 2009) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
if (gc.get(Calendar.DAY_OF_MONTH) == 1) {
  gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_MONTH) == 2) {
  gc.add(Calendar.DATE, 1);

}

}
if (year >= 2010 && year <= 2010) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
gc.add(Calendar.DATE, 1);

}
if (year >= 2011) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
if (gc.get(Calendar.DAY_OF_MONTH) == 1) {
  gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_MONTH) == 2) {
  gc.add(Calendar.DATE, 1);

}

}
break;

// Tag der deutschen Einheit [German Unity Day; public holiday]
case 4052:
if (year >= 1990) {
defined = true;
gc.set(year, Calendar.OCTOBER, 3);

}
break;

// Erntedankfest [Harvest festival]
case 4053:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
break;

// Ende der Sommerzeit (Uhren 1 h zur\u00fcckstellen) [End Daylight Saving Time]
case 4054:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Berlin"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Rosenkranzfest [Our Lady of the Rosary; catholic]
case 4055:
if (year >= 1573 && year <= 1912) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
if (year >= 1913) {
defined = true;
gc.set(year, Calendar.OCTOBER, 7);

}
break;

// Kirchweih
case 4056:
if (year >= 1867) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
break;

// Weltspartag (gefeiert) [World Savings Day (observed)]
case 4057:
if (year >= 1925 && year <= 1988) {
defined = true;
return UNKNOWN;

}
if (year >= 1989) {
defined = true;
gc.set(year, Calendar.OCTOBER, 30);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, -1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, -2);

}

}
break;

// Weltspartag (offiziell) [World Savings Day (official)]
case 4058:
if (year >= 1989) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
break;

// Reformationstag [Reformation Day; public holiday, protestant; BB, MV, SN, ST, TH]
case 4059:
if (year >= 1990 && year <= 2016) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
if (year >= 2018) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
break;

// Reformationstag [Reformation Day; public holiday, protestant; SH, HH, NS, BR]
case 4060:
if (year >= 2018) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
break;

// Reformationstag [Reformation Day; public holiday]
case 4061:
if (year >= 2017 && year <= 2017) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
break;

// Allerheiligen [All Saint's Day; public holiday; BW, BY, NW, RP, SL]
case 4062:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Allerseelen [All Soul's Day; catholic]
case 4063:
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);
break;

// Martinstag [St. Martin's Day]
case 4064:
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);
break;

// Volkstrauertag [National Day of Mourning]
case 4065:
if (year >= 1952) {
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, -14);

}
break;

// Bu\u00df- und Bettag [Day of Repentance and Prayer; public holiday; SA]
case 4066:
if (year >= 1995) {
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, -11);

}
break;

// Bu\u00df- und Bettag [Day of Repentance and Prayer; public holiday]
case 4067:
if (year >= 1990 && year <= 1994) {
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, -11);

}
break;

// Totensonntag (Ewigkeitssonntag) [Sunday of the Dead (Eternity Sunday); protestant]
case 4068:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, -7);
break;

// Nikolaus [Saint Nicholas Day]
case 4069:
defined = true;
gc.set(year, Calendar.DECEMBER, 6);
break;

// Mari\u00e4 Empf\u00e4ngnis [Immaculate Conception]
case 4070:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Christk\u00f6nigsfest [Feast of Christ the King; catholic]
case 4071:
if (year <= 1924) {
defined = true;
return NOT_DEFINED;
}
if (year >= 1925) {
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, -7);

}
break;

// 1. Advent
case 4072:
defined = true;
gc.setAdvent(year);
break;

// 2. Advent
case 4073:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 7);
break;

// 3. Advent (Gaudete)
case 4074:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 14);
break;

// 4. Advent
case 4075:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 21);
break;

// Heiligabend [Christmas Eve]
case 4076:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// 1. Weihnachtsfeiertag (Weihnachten) [Christmas Day; public holiday]
case 4077:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// 2. Weihnachtsfeiertag (Weihnachten) [Boxing Day; public holiday]
case 4078:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Silvester [New Year's Eve]
case 4079:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// Fr\u00fchlingsanfang [March Equinox]
case 4080:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Sommeranfang (Sommersonnenwende) [June Solstice]
case 4081:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Herbstanfang [September Equinox]
case 4082:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Winteranfang (Wintersonnenwende) [December Solstice]
case 4083:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// at
// Neujahrstag
case 5000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Hl. Drei K\u00f6nige [Three Kings Day]
case 5001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Josef
case 5002:
defined = true;
gc.set(year, Calendar.MARCH, 19);
break;

// Beginn der Sommerzeit (Uhren 1 h vorstellen) [Start Daylight Saving Time]
case 5003:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Vienna"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Karfreitag
case 5004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Ostersonntag (Ostern)
case 5005:
defined = true;
gc.setEaster(year,false);
break;

// Ostermontag
case 5006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Staatsfeiertag (Tag der Arbeit) [Labour Day]
case 5007:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Florian
case 5008:
defined = true;
gc.set(year, Calendar.MAY, 4);
break;

// Muttertag [Mother's Day]
case 5009:
if (year >= 1924) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Vatertag [Father's Day]
case 5010:
if (year >= 1955) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.JUNE,
    year);

}
break;

// Christi Himmelfahrt
case 5011:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pfingstsonntag (Pfingsten)
case 5012:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Pfingstmontag
case 5013:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// Fronleichnam
case 5014:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Mari\u00e4 Himmelfahrt
case 5015:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Rupert
case 5016:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 24);
break;

// Tag des Denkmals [European Heritage Days; cultural]
case 5017:
if (year >= 1996) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Tag der Volksabstimmung
case 5018:
if (year >= 1920) {
defined = true;
gc.set(year, Calendar.OCTOBER, 10);

}
break;

// Nationalfeiertag
case 5019:
if (year >= 1965) {
defined = true;
gc.set(year, Calendar.OCTOBER, 26);

}
break;

// Ende der Sommerzeit (Uhren 1 h zur\u00fcckstellen) [End Daylight Saving Time]
case 5020:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Vienna"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Weltspartag (gefeiert)
case 5021:
break;

// Weltspartag (offiziell)
case 5022:
break;

// Allerheiligen
case 5023:
break;

// Martin
case 5024:
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);
break;

// Leopold
case 5025:
defined = true;
gc.set(year, Calendar.NOVEMBER, 15);
break;

// Mari\u00e4 Empf\u00e4ngnis [Immaculate Conception]
case 5026:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Heiliger Abend
case 5027:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Christtag
case 5028:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Stefanitag
case 5029:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Silvester
case 5030:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// Fr\u00fchlingsanfang [Equinox March]
case 5031:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Sommeranfang (Sommersonnenwende) [Solstice June]
case 5032:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Herbstanfang [Equinox September]
case 5033:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Winteranfang (Wintersonnenwende) [Solstice December]
case 5034:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// ch
// Neujahr / Nouvel an / Capodanno / Bumaun [New Year's Day; public]
case 6000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Berchtoldstag (B\u00e4chtelistag) / Saint Berchtold / San Basilio / Fir\u00e0 da Bumaun [Saint Berchtold; public; AG (partly), BE, FR, GL, JU, LU, NE, OW, SH, SO, TG, VD, ZG, ZH]
case 6001:
defined = true;
gc.set(year, Calendar.JANUARY, 2);
break;

// Heilige drei K\u00f6nige / Epiphanie / Epifania [Biblical Magi; public; GR (partly), SZ, TI, UR, LU]
case 6002:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Valentinstag / Saint-Valentin / San Valentino [Valentine's Day]
case 6003:
defined = true;
gc.set(year, Calendar.JANUARY, 14);
break;

// Josefstag / Saint Joseph / San Giuseppe [Saint Joseph's Day; public; cath. regions: GR (partly), LU (partly), NW, SO (partly), SZ, TI, UR, VS]
case 6004:
defined = true;
gc.set(year, Calendar.MARCH, 19);
break;

// [Fat Thursday; LU, NW, UR]
case 6005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
gc.add(Calendar.DATE, -6);
break;

// [Carnival; SZ, GL]
case 6006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
gc.add(Calendar.DATE, -2);
break;

// [Carnival; SZ]
case 6007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
gc.add(Calendar.DATE, -1);
break;

// [Carnival in Basel (begin); only BS]
case 6008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
gc.add(Calendar.DATE, 5);
break;

// [Carnival in Basel (end); only BS]
case 6009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
gc.add(Calendar.DATE, 7);
break;

// [Republic's day; only NE]
case 6010:
defined = true;
gc.set(year, Calendar.MARCH, 1);
break;

// [N\u00e4felser Fahrt; only GL]
case 6011:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.THURSDAY,
    ExtendedGregorianCalendar.APRIL,
    year);
break;

// Muttertag / F\u00eate des M\u00e8res [Mother's Day]
case 6012:
if (year >= 1930) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// V\u00e4tertag [Father's Day]
case 6013:
if (year >= 2007) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.JUNE,
    year);

}
break;

// Vatertag, Josefstag / F\u00eate des P\u00e8res [Father's Day]
case 6014:
defined = true;
gc.set(year, Calendar.MARCH, 19);
break;

// [Independence day; only JU]
case 6015:
defined = true;
gc.set(year, Calendar.JUNE, 23);
break;

// [Je\u00fbne genevois; GE]
case 6016:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
gc.add(Calendar.DATE, 5);
break;

// Europ\u00e4ischer Tag des Denkmals [European Heritage Days; cultural]
case 6017:
if (year >= 1994) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// [Knabenschiessen (begin); only city (and agglomeration) of Zurich]
case 6018:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
break;

// [Knabenschiessen (end); only city (and agglomeration) of Zurich]
case 6019:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SATURDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
gc.add(Calendar.DATE, 2);
break;

// Beginn der Sommerzeit (Uhren 1 h vorstellen) [Daylight Saving Time (begin)]
case 6020:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Zurich"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Karfreitag / Venderdi sontg / Venerd\u00ec santo [Good Friday; public; all of Switzerland except TI, VS]
case 6021:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Ostersonntag (Ostern)
case 6022:
defined = true;
gc.setEaster(year,false);
break;

// Ostermontag / Lundi de P\u00e2ques / Luned\u00ec di Pasqua / Glindesdi da Pasca [Easter Monday; public; all of Switzerland, except VS]
case 6023:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Tag der Arbeit / F\u00eate du travail / Festa del lavoro / Di da la lavur [Labour Day; public; BL, BS, FR (partly), JU, LU (partly), SH, SO (partly), TG, TI, ZH]
case 6024:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Auffahrt / Ascension / Ascensione / Anzainzas [Ascension Day; public; all of Switzerland]
case 6025:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pfingstsonntag (Pfingsten) [Pentecost]
case 6026:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Pfingstmontag / Lundi de Pentec\u00f4te / Luned\u00ec di Pentecoste / Glindesdi da Tschuncaisma [Whit Monday; public; all of Switzerland, except VS]
case 6027:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Fronleichnam / F\u00eate-Dieu / Corpus Domini / Sontgilcrest [Corpus Christi; public; Catholic regions: AG (partly), AI, FR (partly), GR (partly), JU, LU, NW, OW, SO, SZ, TI, UR, VS, ZG]
case 6028:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Peter und Paul / Sts Pierre et Paul / SS. Pietro e Paolo [St. Peter and St. Paul; public; GR (partly), TI]
case 6029:
defined = true;
gc.set(year, Calendar.JUNE, 29);
break;

// Schweizer Nationalfeiertag / F\u00eate nationale Suisse / Festa nazionale della Svizzera / Festa naziunala [Swiss National Day; public; all of Switzerland]
case 6030:
defined = true;
gc.set(year, Calendar.AUGUST, 1);
break;

// Mari\u00e4 Himmelfahrt / Assomption / Assunzione / Assumziun [Assumption of Mary; public; Catholic regions: AG (partly), AI, FR (partly), GR (partly), JU, LU, NW, OW, SO, SZ, TI, UR, VS, ZG]
case 6031:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Eidgen\u00f6ssischer Dank-, Buss- und Bettag / Je\u00fbne f\u00e9d\u00e9ral / Digiuno federale / Festa da la rogaziun federala [Swiss federal fast; public; all of Switzerland, except GE]
case 6032:
if (year >= 1832) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Ende der Sommerzeit (Uhren 1 h zur\u00fcckstellen) [Daylight Saving Time (end)]
case 6033:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Zurich"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Bettagsmontag / Lundi du Je\u00fbne f\u00e9d\u00e9ral / Luned\u00ec del digiuno federale / Gliendischdis da la rogaziun federala [Swiss federal fast Monday; public; VD, NE (partly), BE (partly)]
case 6034:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
gc.add(Calendar.DATE, 1);
break;

// [Bruderklausenfest; only OW]
case 6035:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 25);
break;

// Allerheiligen / Toussaint / Ognissanti / Numnasontga [All Saints; public; Catholic regions: AG (partly), AI, FR (partly), GL, GR (partly), JU, LU, NW, OW, SG, SO, SZ, TI, UR, VS, ZG]
case 6036:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// 1. Advent
case 6037:
defined = true;
gc.setAdvent(year);
break;

// 2. Advent
case 6038:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 7);
break;

// 3. Advent
case 6039:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 14);
break;

// 4. Advent
case 6040:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 21);
break;

// Mari\u00e4 Empf\u00e4ngnis / Immacul\u00e9e Conception / Immacolata Concezione / Immaculata concepziun [Immaculate Conception; public; Catholic regions: AG (partly), AI, FR (partly), GR (partly), LU, NW, OW, SO (partly), SZ, TI, UR, VS, ZG]
case 6041:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Weihnachtstag / No\u00ebl / Natale / Di da Nadal [Christmas; public; all of Switzerland]
case 6042:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Stephanstag / Saint Etienne / Santo Stefano / Son Steffan [St. Stephen's Day; public; all of Switzerland, except GE, JU, VD, VS, NE]
case 6043:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Stephanstag / Saint Etienne / Santo Stefano / Son Steffan [St. Stephen's Day; public; only NE]
case 6044:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
return NOT_DEFINED;
}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
return NOT_DEFINED;
}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
return NOT_DEFINED;
}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
return NOT_DEFINED;
}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
return NOT_DEFINED;
}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
return NOT_DEFINED;
}
break;

// [Restoration of the Republic; only GE]
case 6045:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// fr
// Jour de l'An [New Year's Day]
case 7000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Saint-Valentin [Valentine's Day]
case 7001:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// \u00c9quinoxe mars
case 7002:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [Begin Daylight Saving Time]
case 7003:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Paris"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// P\u00e2ques
case 7004:
defined = true;
gc.setEaster(year,false);
break;

// Lundi de P\u00e2ques
case 7005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// F\u00eate du Travail [Labour Day; public holiday]
case 7006:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Ascension [Ascension Day]
case 7007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Victoire 1945 [V-E Day]
case 7008:
if (year >= 1945) {
defined = true;
gc.set(year, Calendar.MAY, 8);

}
break;

// Pentec\u00f4te
case 7009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Lundi de Pentec\u00f4te [Whit Monday]
case 7010:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// F\u00eate des M\u00e8res [Mother's Day]
case 7011:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
if (false) { // dayname is not implemented 
gc.add(Calendar.DATE, 7);

}
break;

// Solstice juin
case 7012:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// F\u00eate Nationale [Bastille Day]
case 7013:
defined = true;
gc.set(year, Calendar.JULY, 14);
break;

// Assomption [Assumption of Mary]
case 7014:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// \u00c9quinoxe sept
case 7015:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [End Daylight Saving Time]
case 7016:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Paris"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Toussaint [All Saint's Day]
case 7017:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Armistice 1918 [Veterans Day]
case 7018:
if (year >= 1918) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);

}
break;

// Solstice d\u00e9c
case 7019:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// No\u00ebl [Christmas Day]
case 7020:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// La Saint-Sylvestre [New Year's Eve]
case 7021:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// es
// A\u00f1o Nuevo [New Year's Day; National holiday]
case 8000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// [Epiphany; CM, CE, AN, M, S, O, ML]
case 8001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// D\u00eda de Andaluc\u00eda [Day of Andaluc\u00eda; cultural, regional; Andaluc\u00eda (AN)]
case 8002:
defined = true;
gc.set(year, Calendar.FEBRUARY, 28);
break;

// D\u00eda de las Islas Baleares [Day of the Balearic Islands; cultural, regional; Islas Baleares (IB)]
case 8003:
defined = true;
gc.set(year, Calendar.MARCH, 1);
break;

// San Jos\u00e9 [Father's Day; regional; Castile-La Mancha, Madrid, Murcia and Valencia]
case 8004:
defined = true;
gc.set(year, Calendar.MARCH, 19);
break;

// Equinocio de marzo [Vernal equinox]
case 8005:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Jueves Santo [Holy Thursday; All regions except Canary Islands, Catalonia and Valencia (CE, LO, EX, AR, GA, IB, M, CN, ML, CL, MU, O, NA, CM, AN, PV)]
case 8006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Viernes Santo [Good Friday; National holiday]
case 8007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Pascua [Easter Sunday]
case 8008:
defined = true;
gc.setEaster(year,false);
break;

// Lunes de Pascua [Easter Monday; Basque Country, Catalonia, Navarra, Valencia]
case 8009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Cambio de horario de verano [Begin Daylight Saving Time]
case 8010:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Madrid"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// D\u00eda de Arag\u00f3n [D\u00eda de Arag\u00f3n; cultural, regional; Arag\u00f3n]
case 8011:
defined = true;
gc.set(year, Calendar.APRIL, 23);
break;

// San Jorge [St. George's Day; regional; Aragon and Catalonia]
case 8012:
defined = true;
gc.set(year, Calendar.APRIL, 23);
break;

// D\u00eda de Castilla y Le\u00f3n [Castile and Le\u00f3n Day; cultural, regional; Castile and Le\u00f3n]
case 8013:
defined = true;
gc.set(year, Calendar.APRIL, 23);
break;

// D\u00eda del Trabajador [Labor Day; National holiday]
case 8014:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Fiesta de la Comunidad [Fiesta de la Comunidad; cultural, regional; Madrid]
case 8015:
defined = true;
gc.set(year, Calendar.MAY, 2);
break;

// Pentecost\u00e9s [Pentecost]
case 8016:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Secunda Pascua [Whit Monday]
case 8017:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// D\u00eda de las Letras Gallegas(GA) [Galician Literature Day; regional; Galicia]
case 8018:
if (year >= 1963) {
defined = true;
gc.set(year, Calendar.MAY, 17);

}
break;

// D\u00eda de las Canarias [Canarias Day; cultural, regional; The Canary Islands]
case 8019:
defined = true;
gc.set(year, Calendar.MAY, 30);
break;

// D\u00eda de la Regi\u00f3n Castilla-La Mancha [Day of Castile-La Mancha; cultural, regional; Castile-La Mancha]
case 8020:
defined = true;
gc.set(year, Calendar.MAY, 31);
break;

// D\u00eda de la Regi\u00f3n de Murcia [Day of Murcia; cultural, regional, Murcia]
case 8021:
defined = true;
gc.set(year, Calendar.JUNE, 9);
break;

// D\u00eda de La Rioja [Day of Rioja; cultural, regional; La Rioja]
case 8022:
defined = true;
gc.set(year, Calendar.JUNE, 9);
break;

// El Solsticio de junio [June Solstice]
case 8023:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Sant Joan [St. Joan's Day; regional; Catalonia]
case 8024:
defined = true;
gc.set(year, Calendar.JUNE, 24);
break;

// Santiago Apostol [St. James Day; regional; Basque Country, Galicia]
case 8025:
defined = true;
gc.set(year, Calendar.JULY, 25);
break;

// Asunci\u00f3n [Assumption of Mary; National holiday]
case 8026:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// D\u00eda de Ceuta [Day of the independent city Ceuta; cultural, regional; City of Ceuta]
case 8027:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 2);
break;

// D\u00eda de Asturias y Extremadura [Covadonga and Guadalupe Day; regional; Asturias, Extremadura]
case 8028:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 8);
break;

// Fiesta Nacional de Catalu\u00f1a [Day of Catalonia; cultural, regional; Catalonia]
case 8029:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 11);
break;

// Equinoccio de septiembre [Autumnal equinox]
case 8030:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// D\u00eda de Valencia [Day of Valencia; cultural, regional; Valencia]
case 8031:
defined = true;
gc.set(year, Calendar.OCTOBER, 9);
break;

// D\u00eda de la Hispanidad or Fiesta Nacional de Espa\u00f1a [Hispanic Day (Columbus Day); National holiday]
case 8032:
defined = true;
gc.set(year, Calendar.OCTOBER, 12);
break;

// Cambio de horario de invierno [Daylight Saving Time ends]
case 8033:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Madrid"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// D\u00eda de todos los Santos [All Saints Day; National holiday]
case 8034:
break;

// D\u00eda de la Constituci\u00f3n [Constitution Day; National holiday]
case 8035:
defined = true;
gc.set(year, Calendar.DECEMBER, 6);
break;

// Inmaculada Concepci\u00f3n [Immaculate Conception; National holiday]
case 8036:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// El Solsticio de diciembre [December Solstice]
case 8037:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Navidad [Christmas Day; National holiday]
case 8038:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// San Esteban or Segundo d\u00eda de Navidad [St. Stephen's Day; regional; Balearic Islands, Catalonia]
case 8039:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// pt
// Ano Novo [New Year's Day; public holiday]
case 9000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// [Carnival]
case 9001:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// [Begin Daylight Saving Time]
case 9002:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Lisbon"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Sexta-Feira Santa [Good Friday; public holiday]
case 9003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// P\u00e1scoa [Easter Sunday; public holiday]
case 9004:
defined = true;
gc.setEaster(year,false);
break;

// Dia da Liberdade [Freedom Day; public holiday]
case 9005:
if (year >= 1974) {
defined = true;
gc.set(year, Calendar.APRIL, 25);

}
break;

// Dia do Trabalhador [Labour Day; public holiday]
case 9006:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Corpo de Deus [Corpus Christi; public holiday]
case 9007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Dia de Portugal, de Cam\u00f5es e das Comunidades Portuguesas [Portugal Day; public holiday]
case 9008:
if (year >= 1580) {
defined = true;
gc.set(year, Calendar.JUNE, 10);

}
break;

// Assun\u00e7\u00e3o de Nossa Senhora [Assumption of Mary; public holiday]
case 9009:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Implanta\u00e7\u00e3o da Rep\u00fablica [Republic Day; public holiday]
case 9010:
if (year >= 1910) {
defined = true;
gc.set(year, Calendar.OCTOBER, 5);

}
break;

// [End Daylight Saving Time]
case 9011:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Lisbon"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Todos os Santos [All Saint's Day; public holiday]
case 9012:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Restaura\u00e7\u00e3o da Independ\u00eancia [Restoration of Independence]
case 9013:
defined = true;
gc.set(year, Calendar.DECEMBER, 1);
break;

// Imaculada Concei\u00e7\u00e3o [Immaculate Conception's day; public holiday]
case 9014:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Natal [Christmas Day; public holiday]
case 9015:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// it
// Capodanno [New Year's Day; public holiday]
case 10000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Epifania [Epiphany; public holiday]
case 10001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// [Begin Daylight Saving Time]
case 10002:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Rome"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Pasqua [Easter Sunday; public holiday]
case 10003:
defined = true;
gc.setEaster(year,false);
break;

// Luned\u00ec dell'Angelo, Pasquetta [Easter Monday; public holiday]
case 10004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Festa della Liberazione [Anniversary of Liberation; public holiday]
case 10005:
if (year >= 1945) {
defined = true;
gc.set(year, Calendar.APRIL, 25);

}
break;

// Festa dei Lavoratori [Labor Day; public holiday]
case 10006:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Festa della Repubblica [Republic Day; public holiday]
case 10007:
if (year >= 1946) {
defined = true;
gc.set(year, Calendar.JUNE, 2);

}
break;

// San Giovanni
case 10008:
defined = true;
gc.set(year, Calendar.JUNE, 24);
break;

// Assunzione (Ferragosto) [Ferragosto/Assumption Day; public holiday]
case 10009:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// [End Daylight Saving Time]
case 10010:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Rome"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Ognissanti (Tutti i Santi) [All Saints; public holiday]
case 10011:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Il giorno dei morti [Day of the dead; public holiday]
case 10012:
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);
break;

// Giorno della Vittoria [Victory Day; public holiday]
case 10013:
if (year >= 1918) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 4);

}
break;

// Immacolata Concezione (Immacolata) [Immaculate Conception; public holiday]
case 10014:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Natale [Christmas Day; public holiday]
case 10015:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Santo Stefano [St Stephen's Day; public holiday]
case 10016:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// San Silvestro [New Year's Eve]
case 10017:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// gb
// New Year's Day [bank holiday]
case 11000:
if (year >= 1971) {
defined = true;
gc.set(year, Calendar.JANUARY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// 2nd January [bank holiday; Scotland only]
case 11001:
if (year >= 1971) {
defined = true;
gc.set(year, Calendar.JANUARY, 2);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// Burns Night (Burns Supper) [local; Scotland only]
case 11002:
defined = true;
gc.set(year, Calendar.JANUARY, 25);
break;

// St. Patrick's Day [bank holiday; Northern Ireland only]
case 11003:
if (year >= 1971) {
defined = true;
gc.set(year, Calendar.MARCH, 17);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// Good Friday [bank holiday]
case 11004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Easter Sunday
case 11005:
defined = true;
gc.setEaster(year,false);
break;

// Easter Monday [bank holiday; except Scotland]
case 11006:
if (year >= 1871) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);

}
break;

// Saint David's Day [local; Wales, Catholics]
case 11007:
if (year >= 1801 && year <= 2005) {
defined = true;
gc.set(year, Calendar.MARCH, 1);

}
if (year >= 2006 && year <= 2006) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 28);

}
if (year >= 2007) {
defined = true;
gc.set(year, Calendar.MARCH, 1);

}
break;

// Saint David's Day [local; Wales, Anglican Church]
case 11008:
if (year >= 1801 && year <= 2005) {
defined = true;
gc.set(year, Calendar.MARCH, 1);

}
if (year >= 2006 && year <= 2006) {
defined = true;
gc.set(year, Calendar.MARCH, 2);

}
if (year >= 2007) {
defined = true;
gc.set(year, Calendar.MARCH, 1);

}
break;

// Mothering Sunday [local]
case 11009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -21);
break;

// Saint Patrick's Day [local; Northern Ireland]
case 11010:
defined = true;
gc.set(year, Calendar.MARCH, 17);
break;

// Vernal Equinox
case 11011:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/London"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Begin Daylight Saving Time
case 11012:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/London"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// St. George's Day [National day of England]
case 11013:
defined = true;
gc.set(year, Calendar.APRIL, 23);
break;

// Royal Wedding (of Prince William of Wales and Catherine Middleton) [bank holiday]
case 11014:
if (year >= 2011 && year <= 2011) {
defined = true;
gc.set(year, Calendar.APRIL, 29);

}
break;

// May Day Bank Holiday [bank holiday]
case 11015:
if (year >= 1971 && year <= 1994) {
defined = true;
gc.set(year, Calendar.MAY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
if (year >= 1995 && year <= 1995) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
if (year >= 1996) {
defined = true;
gc.set(year, Calendar.MAY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// Whit Monday [bank holiday until 1970; in England, Wales, Northern Ireland]
case 11016:
if (year >= 1871 && year <= 1970) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);

}
break;

// Spring Bank Holiday [bank holiday]
case 11017:
if (year >= 1971 && year <= 2001) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
if (year >= 2002 && year <= 2002) {
defined = true;
gc.set(year, Calendar.JUNE, 4);

}
if (year >= 2003 && year <= 2011) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
if (year >= 2012 && year <= 2012) {
defined = true;
gc.set(year, Calendar.JUNE, 4);

}
if (year >= 2013) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Golden Jubilee of Elizabeth II [bank holiday]
case 11018:
if (year >= 2002 && year <= 2002) {
defined = true;
gc.set(year, Calendar.JUNE, 3);

}
break;

// Queen's Diamond Jubilee [bank holiday]
case 11019:
if (year >= 2012 && year <= 2012) {
defined = true;
gc.set(year, Calendar.JUNE, 5);

}
break;

// Father's Day
case 11020:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
break;

// June Solstice
case 11021:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/London"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Battle of the Boyne - Orangemen's Day [bank holiday; Northern Ireland only]
case 11022:
if (year >= 1971) {
defined = true;
gc.set(year, Calendar.JULY, 12);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// Summer Bank Holiday [bank holiday; in Scotland only]
case 11023:
if (year >= 1971) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);

}
break;

// Summer Bank Holiday [bank holiday; in England, Wales and Northern Ireland]
case 11024:
if (year >= 1971) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);

}
break;

// Autumnal equinox
case 11025:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/London"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Daylight Saving Time ends
case 11026:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/London"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Halloween
case 11027:
defined = true;
gc.set(year, Calendar.OCTOBER, 31);
break;

// Guy Fawkes Night (Bonfire Night)
case 11028:
defined = true;
gc.set(year, Calendar.NOVEMBER, 5);
break;

// St. Andrew's Day [bank holiday; Scotland only]
case 11029:
if (year >= 2007) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 30);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// Autumnal equinox
case 11030:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/London"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Christmas Day [bank holiday]
case 11031:
if (year <= 1970) {
defined = true;
gc.set(year, Calendar.DECEMBER, 25);

}
if (year >= 1971) {
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// Boxing Day, St. Stephen's Day [bank holiday]
case 11032:
if (year <= 1970) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);

}
if (year >= 1971) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// ie
// L\u00e1 Caille / New Year's Day [public holiday]
case 12000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// L\u00e1 Fh\u00e9ile P\u00e1draig / St. Patrick's Day [public holiday]
case 12001:
defined = true;
gc.set(year, Calendar.MARCH, 17);
break;

// Begin Daylight Saving Time
case 12002:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Dublin"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Mothering Sunday [local]
case 12003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -21);
break;

// Domhnach C\u00e1sca / Easter
case 12004:
defined = true;
gc.setEaster(year,false);
break;

// Luan C\u00e1sca / Easter Monday [public holiday]
case 12005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// L\u00e1 an Lucht Oibre
case 12006:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// L\u00e1 Saoire i m\u00ed Mheitheamh / June Holiday [public holiday]
case 12007:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
break;

// Bloomsday
case 12008:
if (year >= 1954) {
defined = true;
gc.set(year, Calendar.JUNE, 16);

}
break;

// L\u00e1 Saoire i m\u00ed L\u00fanasa / August Holiday [public holiday]
case 12009:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);
break;

// End Daylight Saving Time
case 12010:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Dublin"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// L\u00e1 Saoire i m\u00ed Dheireadh F\u00f3mhair / October Holiday [public holiday]
case 12011:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
break;

// L\u00e1 Nollag / Christmas Day [public holiday]
case 12012:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// L\u00e1 Fh\u00e9ile Stiof\u00e1in or L\u00e1 an Dreoil\u00edn / St. Stephen's Day [public holiday]
case 12013:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Equinox March
case 12014:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Dublin"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Solstice June
case 12015:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Dublin"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Equinox September
case 12016:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Dublin"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Solstice December
case 12017:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Dublin"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// se
// Ny\u00e5rsdagen [New Year's Day; public holiday]
case 13000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Trettondagsafton [Twelfth Night; de facto half holiday]
case 13001:
defined = true;
gc.set(year, Calendar.JANUARY, 5);
break;

// Trettondedag jul [Epiphany; public holiday]
case 13002:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Tjugondedag Knut [St. Knut's Day]
case 13003:
defined = true;
gc.set(year, Calendar.JANUARY, 13);
break;

// Karlsdag (Kungens namnsdag)
case 13004:
defined = true;
gc.set(year, Calendar.JANUARY, 28);
break;

// Alla hj\u00e4rtans dag
case 13005:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// Vasaloppet (Wasalauf)
case 13006:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Victoriadag (Kronprizessans namsdag)
case 13007:
defined = true;
gc.set(year, Calendar.MARCH, 12);
break;

// Marie Beb\u00e5delsedag
case 13008:
defined = true;
gc.set(year, Calendar.MARCH, 12);
break;

// Sk\u00e4rtorsdagen [Maundy Thursday; de facto half holiday]
case 13009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// L\u00e5ngfredagen [Good Friday; public holiday]
case 13010:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// P\u00e5skdagen [Easter Sunday; public holiday]
case 13011:
defined = true;
gc.setEaster(year,false);
break;

// Annandag p\u00e5sk [Easter Monday; public holiday]
case 13012:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// F\u00f6rsta maj [May Day; public holiday]
case 13013:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Kristi himmelsf\u00e4rdsdag [Ascension Day; public holiday]
case 13014:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pingstafton [Pentecost Eve; de facto half holiday]
case 13015:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 48);
break;

// Pingstdagen [Pentecost; public holiday]
case 13016:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Annandag pingst [Whit Monday]
case 13017:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// Sveriges nationaldag / svenska flaggans dag [National Day of Sweden / Swedish Flag Day; public holiday]
case 13018:
if (year >= 2005) {
defined = true;
gc.set(year, Calendar.JUNE, 6);

}
break;

// [Begin Daylight Saving Time]
case 13019:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Stockholm"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time]
case 13020:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Stockholm"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Valborgsm\u00e4ssoafton [Walpurgis Night; de facto half holiday]
case 13021:
defined = true;
gc.set(year, Calendar.APRIL, 30);
break;

// Morsdag
case 13022:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// Sveriges nationaldag [National Day of Sweden; public holiday]
case 13023:
defined = true;
gc.set(year, Calendar.JUNE, 6);
break;

// Midsommarafton [Midsummer's Eve; de facto full holiday]
case 13024:
defined = true;
gc.set(year, Calendar.JUNE, 19);
gc.setNextWeekday(Calendar.FRIDAY,false);
break;

// Midsommardagen [Midsummer's Day; public holiday]
case 13025:
defined = true;
gc.set(year, Calendar.JUNE, 20);
gc.setNextWeekday(Calendar.SATURDAY,false);
break;

// Kronprinzessan Victorias f\u00f6delsedag
case 13026:
defined = true;
gc.set(year, Calendar.JULY, 14);
break;

// Kr\u00e4ftskiva [Crayfish Party]
case 13027:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.THURSDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);
break;

// Sk\u00f6rdefest
case 13028:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.LAST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
break;

// Allhelgonaafton [All Saints' Eve; de facto half holiday]
case 13029:
defined = true;
gc.set(year, Calendar.OCTOBER, 30);
gc.setNextWeekday(Calendar.SATURDAY,false);
gc.add(Calendar.DATE, -1);
break;

// Alla helgons dag [All Saints' Day; public holiday]
case 13030:
defined = true;
gc.set(year, Calendar.OCTOBER, 31);
gc.setNextWeekday(Calendar.SATURDAY,false);
break;

// Gustav Adolfdagen
case 13031:
defined = true;
gc.set(year, Calendar.NOVEMBER, 6);
break;

// M\u00e5rtensg\u00e5s
case 13032:
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);
break;

// Fars dag
case 13033:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
break;

// 1. Advent
case 13034:
defined = true;
gc.setAdvent(year);
break;

// Nobeldagen
case 13035:
defined = true;
gc.set(year, Calendar.DECEMBER, 10);
break;

// 2. Advent
case 13036:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 7);
break;

// Luciadagen
case 13037:
defined = true;
gc.set(year, Calendar.DECEMBER, 13);
break;

// 3. Advent (Gaudete)
case 13038:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 14);
break;

// 4. Advent
case 13039:
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, 21);
break;

// Drottnings f\u00f6delsedag
case 13040:
defined = true;
gc.set(year, Calendar.DECEMBER, 23);
break;

// Julafton [Christmas Eve; de facto full holiday]
case 13041:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Juldagen [Christmas Day; public holiday]
case 13042:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Annandag jul [Boxing Day; public holiday]
case 13043:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Ny\u00e5rsafton [New Year's Eve; de facto full holiday]
case 13044:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// V\u00e5rdagj\u00e4mningen Mars [Equinox March]
case 13045:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Sommar Juni [Solstice June]
case 13046:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// H\u00f6stdagj\u00e4mningen September [Equinox September]
case 13047:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Vinter December [Solstice December]
case 13048:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// fi
// Uudenvuodenp\u00e4iv\u00e4 [New Year's Day; public]
case 14000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Loppiainen [Epiphany; public]
case 14001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// nuutinp\u00e4iv\u00e4
case 14002:
defined = true;
gc.set(year, Calendar.JANUARY, 13);
break;

// J. L. Runebergin p\u00e4iv\u00e4
case 14003:
defined = true;
gc.set(year, Calendar.FEBRUARY, 5);
break;

// Laskiainen
case 14004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// Kalevalan p\u00e4iv\u00e4 [Kalevalatag]
case 14005:
defined = true;
gc.set(year, Calendar.FEBRUARY, 28);
break;

// Pitk\u00e4perjantai [Good Friday; public]
case 14006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// P\u00e4\u00e4si\u00e4isp\u00e4iv\u00e4 [Easter Sunday; public]
case 14007:
defined = true;
gc.setEaster(year,false);
break;

// 2. P\u00e4\u00e4si\u00e4isp\u00e4iv\u00e4 [Easter Monday; public]
case 14008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Vappu [May Day; public]
case 14009:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Mikael Agricolan p\u00e4iv\u00e4
case 14010:
defined = true;
gc.set(year, Calendar.APRIL, 9);
break;

// kansallinen veteraanip\u00e4iv\u00e4
case 14011:
defined = true;
gc.set(year, Calendar.APRIL, 27);
break;

// J. V. Snellmanin p\u00e4iv\u00e4
case 14012:
defined = true;
gc.set(year, Calendar.MAY, 12);
break;

// Floratag
case 14013:
if (year >= 1848) {
defined = true;
gc.set(year, Calendar.MAY, 13);

}
break;

// [Mother's Day]
case 14014:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// [Memorial Day]
case 14015:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// Helatorstai [Ascension Day; public]
case 14016:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Helluntaip\u00e4iv\u00e4 [Pentecost; public]
case 14017:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// puolustusvoimain lippujuhlan p\u00e4iv\u00e4
case 14018:
defined = true;
gc.set(year, Calendar.JUNE, 4);
break;

// Juhannusaatto [Midsummer's Eve; de facto full holiday]
case 14019:
defined = true;
gc.set(year, Calendar.JUNE, 19);
gc.setNextWeekday(Calendar.FRIDAY,false);
break;

// Suomen lipun p\u00e4iv\u00e4 [Midsummer's Day; public holiday]
case 14020:
if (year <= 1954) {
defined = true;
gc.set(year, Calendar.JUNE, 24);

}
if (year >= 1955) {
defined = true;
gc.set(year, Calendar.JUNE, 20);
gc.setNextWeekday(Calendar.SATURDAY,false);

}
break;

// Eino-Leino-Tag
case 14021:
defined = true;
gc.set(year, Calendar.JULY, 6);
break;

// Aleksis Kiven p\u00e4iv\u00e4
case 14022:
defined = true;
gc.set(year, Calendar.OCTOBER, 10);
break;

// Yhdistyneiden Kansakuntien p\u00e4iv\u00e4
case 14023:
defined = true;
gc.set(year, Calendar.OCTOBER, 24);
break;

// [All Saints' Day; public holiday]
case 14024:
defined = true;
gc.set(year, Calendar.OCTOBER, 31);
gc.setNextWeekday(Calendar.SATURDAY,false);
break;

// ruotsalaisuuden p\u00e4iv\u00e4
case 14025:
defined = true;
gc.set(year, Calendar.NOVEMBER, 6);
break;

// Is\u00e4np\u00e4iv\u00e4 [Father's Day]
case 14026:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
break;

// Itsen\u00e4isyysp\u00e4iv\u00e4 [Independence Day; public]
case 14027:
defined = true;
gc.set(year, Calendar.DECEMBER, 6);
break;

// Lucia-neito
case 14028:
defined = true;
gc.set(year, Calendar.DECEMBER, 13);
break;

// Jouluaatto [Christmas Eve; non official, de facto full holiday]
case 14029:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Joulup\u00e4iv\u00e4 [Christmas Day; public]
case 14030:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Tapaninp\u00e4iv\u00e4 [St. Stephen's Day; public]
case 14031:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// [Begin Daylight Saving Time]
case 14032:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Helsinki"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time]
case 14033:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Helsinki"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// no
// Nytt\u00e5rsdag [New Year's Day; public holiday]
case 15000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// [Begin Daylight Saving Time]
case 15001:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Oslo"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Palmes\u00f8ndag [Palm Sunday]
case 15002:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -7);
break;

// Skj\u00e6rtorsdag [Maundy Thursday]
case 15003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Langfredag [Good Friday; public holiday]
case 15004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// F\u00f8rste p\u00e5skedag [Easter Sunday; public holiday]
case 15005:
defined = true;
gc.setEaster(year,false);
break;

// Andre p\u00e5skedag [Easter Monday; public holiday]
case 15006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// F\u00f8rste mai [Labour Day; public holiday]
case 15007:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Morsdag [Mother's Day]
case 15008:
if (year >= 1919) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);

}
break;

// Syttende mai or Grunnlovsdagen [Constitution Day; public holiday]
case 15009:
if (year >= 1814) {
defined = true;
gc.set(year, Calendar.MAY, 17);

}
break;

// Kristi himmelfartsdag [Ascension Day; public holiday]
case 15010:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// F\u00f8rste pinsedag [Whit Sunday; public holiday]
case 15011:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// [Whit Monday; public holiday]
case 15012:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 51);
break;

// [End Daylight Saving Time]
case 15013:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Oslo"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Julaften [Christmas Eve]
case 15014:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// F\u00f8rste juledag [Christmas Day; public holiday]
case 15015:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Andre juledag [Boxing Day; public holiday]
case 15016:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// nl
// Nieuwjaar [New Year's Day; public holiday]
case 16000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Goede Vrijdag [Good Friday; public holiday]
case 16001:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Pasen (Sunday) [Easter Sunday; public holiday]
case 16002:
defined = true;
gc.setEaster(year,false);
break;

// Pasen (Monday) [Easter Monday; public holiday]
case 16003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Prinsessedag [Princess' Day]
case 16004:
if (year >= 1885 && year <= 1890) {
defined = true;
gc.set(year, Calendar.AUGUST, 31);

}
break;

// Koninginnedag [Queen's day; public holiday]
case 16005:
if (year >= 1891 && year <= 1948) {
defined = true;
gc.set(year, Calendar.AUGUST, 31);

}
if (year >= 1949) {
defined = true;
gc.set(year, Calendar.APRIL, 30);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, -1);

}

}
break;

// Dodenherdenking [Remembrance of the dead; national holiday]
case 16006:
defined = true;
gc.set(year, Calendar.MAY, 4);
break;

// Bevrijdingsdag [Liberation day; public holiday]
case 16007:
if (year >= 1945 && year <= 1989 && (year - 1945)%5 == 0) {
defined = true;
gc.set(year, Calendar.MAY, 5);

}
if (year >= 1990) {
defined = true;
gc.set(year, Calendar.MAY, 5);

}
break;

// [Mother's Day]
case 16008:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// Hemelvaartsdag [Ascension of Jesus; public holiday]
case 16009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pinksteren (Sunday) [Pentecost (Sunday); public holiday]
case 16010:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Pinksteren (Monday) [Pentecost (Monday); public holiday]
case 16011:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// Sinterklaas [Saint Nicholas' Eve]
case 16012:
defined = true;
gc.set(year, Calendar.DECEMBER, 5);
break;

// Eerste Kerstdag [Christmas Day; public holiday]
case 16013:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Tweede Kerstdag [Christmas Day; public holiday]
case 16014:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// [Begin Daylight Saving Time (DST)]
case 16015:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Amsterdam"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 16016:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Amsterdam"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// dk
// Nyt\u00e5rsdag [New Year's Day; public]
case 17000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Prinsesse Marys f\u00f8dselsdag [Crown Princess Mary's birthday; other]
case 17001:
defined = true;
gc.set(year, Calendar.FEBRUARY, 5);
break;

// [Valentine's Day]
case 17002:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// Fastelavn [Fastelavn; other]
case 17003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -49);
break;

// For\u00e5rsj\u00e6vnd\u00f8gn [Vernal equinox]
case 17004:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Palmes\u00f8ndag [Palm Sunday; public]
case 17005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -7);
break;

// Sk\u00e6rtorsdag [Maundy Thursday; public]
case 17006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Langfredag [Good Friday; public]
case 17007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// P\u00e5skedag [Easter Sunday; public]
case 17008:
defined = true;
gc.setEaster(year,false);
break;

// 2. P\u00e5skedag [Easter Monday; public]
case 17009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// 1. April [April Fools' Day]
case 17010:
defined = true;
gc.set(year, Calendar.APRIL, 1);
break;

// Danmarks bes\u00e6ttelse [Occupation of Denmark]
case 17011:
defined = true;
gc.set(year, Calendar.APRIL, 9);
break;

// Dronningens f\u00f8dselsdag [Birthday of Queen Margrethe II]
case 17012:
defined = true;
gc.set(year, Calendar.APRIL, 16);
break;

// Prinsesse Benedikte f\u00f8dselsdag [Princess Benedikte's birthday]
case 17013:
defined = true;
gc.set(year, Calendar.APRIL, 29);
break;

// Arbejdernes kampdag / 1. maj [Labour Day]
case 17014:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Danmarks befrielse [Liberation day; other]
case 17015:
defined = true;
gc.set(year, Calendar.MAY, 5);
break;

// Kronprins Frederiks f\u00f8dselsdag [Birthday of Crown Prince Frederik]
case 17016:
defined = true;
gc.set(year, Calendar.MAY, 26);
break;

// Sommertid starter [Begin Daylight Saving Time]
case 17017:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Copenhagen"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Store Bededag [Great Prayer Day; public]
case 17018:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 26);
break;

// Kristi Himmelfartsdag [Ascension Day; public]
case 17019:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pinsedag [Whit Sunday; public]
case 17020:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// 2. pinsedag [Whit Monday; public]
case 17021:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// Grundlovsdag [Constitution Day; public]
case 17022:
defined = true;
gc.set(year, Calendar.JUNE, 5);
break;

// Sommersolhverv [June Solstice]
case 17023:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Efter\u00e5rsj\u00e6vnd\u00f8gn [Autumnal equinox]
case 17024:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Prins Joachims f\u00f8dselsdag [Birthday of Prince Joachim; other]
case 17025:
defined = true;
gc.set(year, Calendar.JUNE, 7);
break;

// Prins Henriks f\u00f8dselsdag [Birthday of Prince Henrik; other]
case 17026:
defined = true;
gc.set(year, Calendar.JUNE, 11);
break;

// Valdemarsdag og Genforeningsdag [Day of Valdemar and Reunion day; other]
case 17027:
defined = true;
gc.set(year, Calendar.JUNE, 15);
break;

// Sankt Hans aften [Saint John's Eve; other]
case 17028:
defined = true;
gc.set(year, Calendar.JUNE, 23);
break;

// Sommertid slutter [End Daylight Saving Time]
case 17029:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Copenhagen"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Mortensaften [The eve before Saint Martin's day; other]
case 17030:
defined = true;
gc.set(year, Calendar.NOVEMBER, 10);
break;

// Vintersolhverv [December Solstice]
case 17031:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Sankt Lucia [Saint Lucy's Day; other]
case 17032:
defined = true;
gc.set(year, Calendar.DECEMBER, 13);
break;

// Juleaftensdag [Christmas Eve; public]
case 17033:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// 1. Juledag [First Day of Christmas; public]
case 17034:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// 2. juledag [Second Day of Christmas; public]
case 17035:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Nyt\u00e5rsaften [New Year's Eve]
case 17036:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// be
// Nieuwjaar / Nouvel An / Neujahr [New Year's Day; plublic holiday]
case 18000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Driekoningen / \u00c9piphanie / Erscheinung des Herrn [Epiphany; non official public holiday]
case 18001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Valentijnsdag / Saint-Valentin / Valentinstag [Valentine's Day; non official public holiday]
case 18002:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// Pasen / P\u00e2ques / Ostern [Easter; plublic holiday]
case 18003:
defined = true;
gc.setEaster(year,false);
break;

// Paasmaandag / Lundi de P\u00e2ques / Ostermontag [Easter Monday; plublic holiday]
case 18004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Dag van de arbeid / F\u00eate du Travail / Tag der Arbeit [Labour Day; plublic holiday]
case 18005:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Feest van de Vlaamse Gemeenschap [Day of the Flemish Community; non plublic holiday; observed in Flemish Community only]
case 18006:
defined = true;
gc.set(year, Calendar.JULY, 11);
break;

// Onze Lieve Heer hemelvaart / Ascension / Christi Himmelfahrt [Ascension; plublic holiday]
case 18007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// Pinksteren / Pentec\u00f4te / Pfingsten [Pentecost; plublic holiday]
case 18008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// Pinkstermaandag / Lundi de Pentec\u00f4te / Pfingstmontag [Pentecost Monday; plublic holiday]
case 18009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);
break;

// Nationale feestdag / F\u00eate nationale / Nationalfeiertag [National holiday; plublic holiday]
case 18010:
defined = true;
gc.set(year, Calendar.JULY, 21);
break;

// F\u00eate de la Communaut\u00e9 fran\u00e7aise de Belgique [Day of the French Community of Belgium; non plublic holiday; in the French Community only]
case 18011:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 27);
break;

// Onze Lieve Vrouw hemelvaart / Assomption / Mari\u00e4 Himmelfahrt [Assumption of Mary; plublic holiday]
case 18012:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Allerheiligen / Toussaint / Allerheiligen [All Saints; public holiday]
case 18013:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Allerzielen / F\u00eate des morts / Allerseelen [All Soul's Day]
case 18014:
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);
break;

// Wapenstilstand / Jour de l'armistice / Waffenstillstand [Armistice Day; public holiday]
case 18015:
if (year >= 1918) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);

}
break;

// Tag der Deutschsprachigen Gemeinschaft Belgiens [Day of the German-speaking Community of Belgium; non public holiday; in the German-speaking region only]
case 18016:
defined = true;
gc.set(year, Calendar.NOVEMBER, 15);
break;

// Koningsdag / F\u00eate du Roi / Festtag des K\u00f6nigs [King's Feast; non public holiday]
case 18017:
defined = true;
gc.set(year, Calendar.NOVEMBER, 15);
break;

// Sinterklaas / Saint-Nicolas (f\u00eate) / Sankt Nikolaus [Saint Nicholas; non public holiday]
case 18018:
defined = true;
gc.set(year, Calendar.DECEMBER, 6);
break;

// Kerstmis / No\u00ebl / Weihnacht [Christmas; plublic holiday]
case 18019:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// gr
// \u03a0\u03c1\u03c9\u03c4\u03bf\u03c7\u03c1\u03bf\u03bd\u03b9\u03ac / Protochronia [New Year's Day; public holiday]
case 19000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// \u0398\u03b5\u03bf\u03c6\u03ac\u03bd\u03b5\u03b9\u03b1 / Theofania [Epiphany; public holiday]
case 19001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// \u03a4\u03c1\u03b9\u03ce\u03bd \u0399\u03b5\u03c1\u03b1\u03c1\u03c7\u03ce\u03bd / Trion Ierarchon [The Three Holy Hierarchs; school holiday only]
case 19002:
defined = true;
gc.set(year, Calendar.JANUARY, 30);
break;

// \u039a\u03b1\u03b8\u03b1\u03c1\u03ae \u0394\u03b5\u03c5\u03c4\u03ad\u03c1\u03b1 / Kathari Devtera [Ash Monday (eastern churches); public holiday]
case 19003:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, -48);
break;

// \u0395\u03c5\u03b1\u03b3\u03b3\u03b5\u03bb\u03b9\u03c3\u03bc\u03cc\u03c2 \u0398\u03b5\u03bf\u03c4\u03cc\u03ba\u03bf\u03c5 / Eikosti-pempti Martiou [25th of March; public holiday]
case 19004:
defined = true;
gc.set(year, Calendar.MARCH, 25);
break;

// [Begin Daylight Saving Time]
case 19005:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Athens"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// \u039a\u03c5\u03c1\u03b9\u03b1\u03ba\u03ae \u03c4\u03bf\u03c5 \u03a0\u03ac\u03c3\u03c7\u03b1 / Kyriaki tou Pascha [Easter Sunday (eastern churches)]
case 19006:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
break;

// \u0394\u03b5\u03c5\u03c4\u03ad\u03c1\u03b1 \u03c4\u03bf\u03c5 \u03a0\u03ac\u03c3\u03c7\u03b1 / Deutera tou Pascha [Easter Monday (eastern churches); public holiday]
case 19007:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, 1);
break;

// \u0395\u03c1\u03b3\u03b1\u03c4\u03b9\u03ba\u03ae \u03a0\u03c1\u03c9\u03c4\u03bf\u03bc\u03b1\u03b3\u03b9\u03ac / Ergatiki Protomagia [Labour Day; public holiday]
case 19008:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// \u03a0\u03bd\u03b5\u03c5\u03bc\u03b1\u03c4\u03bf\u03c2 / Pnevmatos [Pentecost (eastern churches); public holiday]
case 19009:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, 49);
break;

// \u0391\u03b3\u03b9\u03bf\u03c5 \u03a0\u03bd\u03b5\u03c5\u03bc\u03b1\u03c4\u03bf\u03c2 / Tou Agiou Pnevmatos [Pentecost Monday (eastern churches); public holiday]
case 19010:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, 50);
break;

// \u0397 \u039a\u03bf\u03af\u03bc\u03b7\u03c3\u03b9\u03c2 \u03c4\u03b7\u03c2 \u0398\u03b5\u03bf\u03c4\u03cc\u03ba\u03bf\u03c5 / Panaghias [The Dormition of the Holy Virgin; public holiday]
case 19011:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// [End Daylight Saving Time]
case 19012:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Athens"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// \u03a4\u03bf \u038c\u03c7\u03b9 / To Ochi [The Ochi day; public holiday]
case 19013:
if (year >= 1942) {
defined = true;
gc.set(year, Calendar.OCTOBER, 28);

}
break;

// \u03a0\u03bf\u03bb\u03c5\u03c4\u03b5\u03c7\u03bd\u03b5\u03af\u03bf / Polytechneio [Polytechneio; school holiday only]
case 19014:
if (year >= 1973) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 17);

}
break;

// \u03a7\u03c1\u03b9\u03c3\u03c4\u03bf\u03cd\u03b3\u03b5\u03bd\u03bd\u03b1 / Christougenna [Christmas Day; public holiday]
case 19015:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// \u03a3\u03cd\u03bd\u03b1\u03be\u03b9\u03c2 \u0398\u03b5\u03bf\u03c4\u03cc\u03ba\u03bf\u03c5 / Synaxis Theotokou [Boxing Day; public holiday]
case 19016:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// ro
// Anul nou [New Year's Day; Official non-working holiday]
case 20000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// 2. Anul nou [Day after New Year's Day; Official non-working holiday]
case 20001:
defined = true;
gc.set(year, Calendar.JANUARY, 2);
break;

// Ziua Unirii [Unification Day; Official holiday]
case 20002:
defined = true;
gc.set(year, Calendar.JANUARY, 24);
break;

// Dragobetele [St. Valentine's Day; Traditional festival]
case 20003:
defined = true;
gc.set(year, Calendar.FEBRUARY, 24);
break;

// M\u0103r\u0163i\u015forul [Spring festival; Traditional festival]
case 20004:
defined = true;
gc.set(year, Calendar.MARCH, 1);
break;

// Ziua Unirii [Women's Day; Official holiday]
case 20005:
defined = true;
gc.set(year, Calendar.MARCH, 8);
break;

// Ziua Mamei [Mother's Day]
case 20006:
if (year >= 2010) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Ziua_Tat\u0103lui [Father's Day]
case 20007:
if (year >= 2010) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Pa\u015ftele [Easter; Official non-working holiday]
case 20008:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
break;

// [NATO Day; Not a public holiday]
case 20009:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.APRIL,
    year);
break;

// Ziua Eroilor (\u00cen\u0103l\u0163area) [Heroes' Day (Ascension); Official non-working holiday]
case 20010:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, 40);
break;

// 1. Rusaliile [Pentecost, Whit Monday; Official non-working holiday]
case 20011:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, 50);
break;

// 2. Rusaliile [Whit Monday; Official non-working holiday]
case 20012:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, 51);
break;

// [Begin Daylight Saving Time]
case 20013:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Bucharest"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// Ziua muncii [Labour Day; Official non-working holiday]
case 20014:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Ziua Copilului [Child's day; Official holiday]
case 20015:
defined = true;
gc.set(year, Calendar.JUNE, 1);
break;

// Ziua Tricolorului [Flag Day; Official holiday]
case 20016:
if (year >= 1848) {
defined = true;
gc.set(year, Calendar.JUNE, 26);

}
break;

// Ziua Imnului na\u0163ional [National Anthem Day; Official holiday]
case 20017:
defined = true;
gc.set(year, Calendar.JULY, 29);
break;

// Adormirea Maicii Domnului [Dormition of the Theotokos; Official non-working holiday]
case 20018:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Armed Forces Day [Armed Forces Day; Not a public holiday]
case 20019:
defined = true;
gc.set(year, Calendar.OCTOBER, 25);
break;

// Ziua Na\u0163ional\u0103 (Ziua Marii Uniri) [National holiday (Great Union Day); Official non-working holiday]
case 20020:
defined = true;
gc.set(year, Calendar.DECEMBER, 1);
break;

// Ziua Constitu\u0163iei [Constitution Day]
case 20021:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Cr\u0103ciunul [Christmas Day; Official non-working holiday]
case 20022:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// A doua zi de Cr\u0103ciun [Boxing Day; Official non-working holiday]
case 20023:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// [End Daylight Saving Time]
case 20024:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Bucharest"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// si
// novo leto [New Year's Day; state holiday, work-free]
case 21000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// 2. novo leto [New Year's Day; state holiday, work-free]
case 21001:
defined = true;
gc.set(year, Calendar.JANUARY, 2);
break;

// Pre\u0161ernov dan, slovenski kulturni praznik [The Slovenian Cultural Holiday; state holiday, work-free]
case 21002:
if (year >= 1944) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 8);

}
break;

// [Begin Daylight Saving Time]
case 21003:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Ljubljana"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// velika no\u010d in velikono\u010dni ponedeljek [Easter Sunday; work-free day]
case 21004:
defined = true;
gc.setEaster(year,false);
break;

// 2. velika no\u010d in velikono\u010dni ponedeljek [Easter Monday; work-free day]
case 21005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// dan upora proti okupatorju [Day of Uprising Against Occupation; state holiday, work-free]
case 21006:
defined = true;
gc.set(year, Calendar.APRIL, 27);
break;

// praznik dela [Labour Day; state holiday]
case 21007:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// 2. praznik dela [2. Labour Day; state holiday]
case 21008:
defined = true;
gc.set(year, Calendar.MAY, 2);
break;

// binko\u0161tna nedelja [Pentecost; work-free day]
case 21009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// dan dr\u017eavnosti [Statehood Day; state holiday]
case 21010:
defined = true;
gc.set(year, Calendar.JUNE, 25);
break;

// Marijino vnebovzetje [Assumption Day; work-free day]
case 21011:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// dan zdru\u017eitve prekmurskih Slovencev z mati\u010dnim narodom po prvi svetovni vojni [Union of the Slovenians in Prekmurje with the mother nation after the First World War; state holiday, not work-free]
case 21012:
defined = true;
gc.set(year, Calendar.AUGUST, 17);
break;

// [End Daylight Saving Time]
case 21013:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Ljubljana"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// dan vrnitve Primorske k mati\u010dni domovini [Reunion of Slovenian Littoral with the motherland; state holiday, not work-free]
case 21014:
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);
break;

// dan reformacije [Reformation Day; work-free day]
case 21015:
defined = true;
gc.set(year, Calendar.OCTOBER, 15);
break;

// dan spomina na mrtve [Remembrance day; state holiday, work-free]
case 21016:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// dan Rudolfa Maistra [Rudolf Maister Day; state holiday, not work-free]
case 21017:
defined = true;
gc.set(year, Calendar.NOVEMBER, 23);
break;

// Bo\u017ei\u010d [Christmas Day; work-free day]
case 21018:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// dan samostojnosti in enotnosti [Independence and Unity Day; state holiday, work free]
case 21019:
if (year >= 1990) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);

}
break;

// pl
// [New Year's Day]
case 22000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Dzie\u0144 Flagi Rzeczypospolitej Polskiej [Flag of the Republic of Poland Day (Flag Day); non public holiday]
case 22001:
if (year >= 2004) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 2);

}
break;

// pierwszy dzie\u0144 Wielkiej Nocy [Easter Sunday; public holiday]
case 22002:
defined = true;
gc.setEaster(year,false);
break;

// drugi dzie\u0144 Wielkiej Nocy [Easter Monday; public holiday]
case 22003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// \u015awi\u0119to Pa\u0144stwowe [State Holiday]
case 22004:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// \u015awi\u0119to Narodowe Trzeciego Maja [Constitution Day]
case 22005:
defined = true;
gc.set(year, Calendar.MAY, 3);
break;

// pierwszy dzie\u0144 Zielonych \u015awi\u0105tek [Pentecost]
case 22006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);
break;

// dzie\u0144 Bo\u017cego Cia\u0142a (Bo\u017ce Cia\u0142o) [Corpus Christi]
case 22007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Wniebowzi\u0119cie Naj\u015bwi\u0119tszej Maryi Panny [Assumption of the Blessed Virgin Mary]
case 22008:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
break;

// Wszystkich \u015awi\u0119tych [All Saint's Day; public holiday]
case 22009:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// [Independence Day]
case 22010:
if (year >= 1918) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);

}
break;

// pierwszy dzie\u0144 Bo\u017cego Narodzenia [1st day of Christmas; public holiday]
case 22011:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// drugi dzie\u0144 Bo\u017cego Narodzenia [2nd day of Christmas; public holiday]
case 22012:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// [DST start]
case 22013:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Europe/Warsaw"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time]
case 22014:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Europe/Warsaw"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [Equinox March]
case 22015:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [Solstice June]
case 22016:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [Equinox September]
case 22017:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [Solstice December]
case 22018:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// ru
// \u041d\u043e\u0432\u044b\u0439 \u0413\u043e\u0434 / Novy God [New Year's Day; public holiday]
case 23000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// \u0420\u043e\u0436\u0434\u0435\u0441\u0442\u0432\u043e / Rozhdestvo [Orthodox Christmas Day; public holiday]
case 23001:
defined = true;
gc.set(year, Calendar.JANUARY, 7);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// \u0414\u0435\u043d\u044c \u0441\u0432\u044f\u0442\u043e\u0433\u043e \u0412\u0430\u043b\u0435\u043d\u0442\u0438\u043d\u0430 [Valentine's Day]
case 23002:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// \u041c\u0430\u0441\u043b\u0435\u043d\u0438\u0446\u0430 / Maslenitsa [Carnival]
case 23003:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
gc.add(Calendar.DATE, -47);
break;

// \u0414\u0435\u043d\u044c \u0417\u0430\u0449\u0438\u0442\u043d\u0438\u043a\u0430 \u041e\u0442\u0435\u0447\u0435\u0441\u0442\u0432\u0430 / Den' zashchitnika Otechestva [Defender of the Fatherland Day]
case 23004:
defined = true;
gc.set(year, Calendar.FEBRUARY, 23);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// \u041c\u0435\u0436\u0434\u0443\u043d\u0430\u0440\u043e\u0434\u043d\u044b\u0439 \u0416\u0435\u043d\u0441\u043a\u0438\u0439 \u0414\u0435\u043d\u044c [International Women's Day; public holiday]
case 23005:
defined = true;
gc.set(year, Calendar.MARCH, 8);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// \u0414\u0435\u043d\u044c \u0432\u0435\u0441\u043d\u044b \u0438 \u0442\u0440\u0443\u0434\u0430 [Spring and Labour Day; public holiday]
case 23006:
defined = true;
gc.set(year, Calendar.MAY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// \u0414\u0435\u043d\u044c \u041f\u043e\u0431\u0435\u0434\u044b [Victory Day; public holiday]
case 23007:
if (year >= 1945) {
defined = true;
gc.set(year, Calendar.MAY, 9);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// \u0414\u0435\u043d\u044c \u0420\u043e\u0441\u0441\u0438\u0438 [Russia Day; public holiday]
case 23008:
defined = true;
gc.set(year, Calendar.JUNE, 12);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// \u0414\u0435\u043d\u044c \u043d\u0430\u0440\u043e\u0434\u043d\u043e\u0433\u043e \u0435\u0434\u0438\u043d\u0441\u0442\u0432\u0430 [Unity Day; public holiday]
case 23009:
if (year >= 2005) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 4);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}

}
break;

// \u041f\u0430\u0441\u0445\u0430 [Easter]
case 23010:
defined = true;
if (year < 1900 || year > 2099) return NOT_DEFINED;gc.setEaster(year, true);
gc.add(Calendar.DATE, 13);
break;

// \u0414\u0435\u043d\u044c \u0417\u043d\u0430\u043c\u0435\u043d\u0438
case 23011:
defined = true;
gc.set(year, Calendar.AUGUST, 22);
break;

// \u0414\u0435\u043d\u044c \u041a\u043e\u043d\u0441\u0442\u0438\u0442\u0443\u0446\u0438\u0438
case 23012:
defined = true;
gc.set(year, Calendar.DECEMBER, 12);
break;

// am
// \u0531\u0574\u0561\u0576\u0585\u0580 [New Year's Day; public]
case 24000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// \u0546\u0561\u056d\u0561\u056e\u0576\u0576\u0564\u0575\u0561\u0576 \u057f\u0578\u0576\u0565\u0580 [Christmas Eve; public]
case 24001:
defined = true;
gc.set(year, Calendar.JANUARY, 5);
break;

// \u054d\u0578\u0582\u0580\u0562 \u053e\u0576\u0578\u0582\u0576\u0564 [Christmas Day; public]
case 24002:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// \u054d\u0578\u0582\u0580\u0562 \u053e\u0576\u0576\u0564\u0575\u0561\u0576 \u0587 \u0540\u0561\u0575\u057f\u0576\u0578\u0582\u0569\u0575\u0561\u0576 \u057f\u0578\u0576\u056b\u0576 \u0570\u0561\u057b\u0578\u0580\u0564\u0578\u0572` \u0544\u0565\u057c\u0565\u056c\u0578\u0581 \u0570\u056b\u0577\u0561\u057f\u0561\u056f\u056b \u0585\u0580 [The day of commemoration of all dead people after Christmas Day; public]
case 24003:
defined = true;
gc.set(year, Calendar.JANUARY, 7);
break;

// \u0532\u0561\u0576\u0561\u056f\u056b \u0585\u0580 [Army Day; public]
case 24004:
if (year >= 1992) {
defined = true;
gc.set(year, Calendar.JANUARY, 28);

}
break;

// \u053f\u0561\u0576\u0561\u0576\u0581 \u057f\u0578\u0576 [International Women's Day; public]
case 24005:
if (year >= 1921) {
defined = true;
gc.set(year, Calendar.MARCH, 8);

}
break;

// \u0535\u0572\u0565\u057c\u0576\u056b \u0566\u0578\u0570\u0565\u0580\u056b \u0570\u056b\u0577\u0561\u057f\u0561\u056f\u056b \u0585\u0580 [Genocide Remembrance Day; public]
case 24006:
if (year >= 1915) {
defined = true;
gc.set(year, Calendar.APRIL, 24);

}
break;

// \u0540\u0561\u0572\u0569\u0561\u0576\u0561\u056f\u056b \u0565\u0582 \u053d\u0561\u0572\u0561\u0572\u0578\u0582\u0569\u0575\u0561\u0576 \u057f\u0578\u0576 [Victory and Peace Day; public]
case 24007:
if (year >= 1945) {
defined = true;
gc.set(year, Calendar.MAY, 9);

}
break;

// \u0540\u0561\u0576\u0580\u0561\u057a\u0565\u057f\u0578\u0582\u0569\u0575\u0561\u0576 \u0585\u0580 [Republic Day; public]
case 24008:
if (year >= 1918) {
defined = true;
gc.set(year, Calendar.MAY, 28);

}
break;

// \u054d\u0561\u0570\u0574\u0561\u0576\u0561\u0564\u0580\u0578\u0582\u0569\u0575\u0561\u0576 \u0585\u0580 [Constitution Day; public]
case 24009:
if (year >= 1995) {
defined = true;
gc.set(year, Calendar.JULY, 5);

}
break;

// \u0531\u0576\u056f\u0561\u056d\u0578\u0582\u0569\u0575\u0561\u0576 \u0585\u0580 [Independence Day; public]
case 24010:
if (year >= 1991) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 21);

}
break;

// \u0535\u0580\u056f\u0580\u0561\u0577\u0561\u0580\u056a\u056b \u0566\u0578\u0570\u0565\u0580\u056b \u0570\u056b\u0577\u0561\u057f\u0561\u056f\u056b \u0585\u0580 [1988 Earthquake Memorial Day (Spitak Remembrance Day); public]
case 24011:
if (year >= 1988) {
defined = true;
gc.set(year, Calendar.DECEMBER, 7);

}
break;

// \u054e\u0561\u0580\u0564\u0587\u0561\u0580 [Vardevar]
case 24012:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 98);
break;

// mx
// A\u00f1o Nuevo [New Year's Day; statutory holiday]
case 25000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// D\u00eda de la Constituci\u00f3n [Constitution day; statutory holiday]
case 25001:
if (year >= 1917 && year <= 2005) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 5);

}
if (year >= 2006) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);

}
break;

// Natalicio de Benito Ju\u00e1rez [Benito Ju\u00e1rez's birthday; statutory holiday]
case 25002:
if (year >= 1858) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);

}
break;

// D\u00eda del Trabajo [Labour Day; statutory holiday]
case 25003:
if (year >= 1906) {
defined = true;
gc.set(year, Calendar.MAY, 1);

}
break;

// D\u00eda de Independencia [Independence Day; statutory holiday]
case 25004:
if (year >= 1826) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 16);

}
break;

// D\u00eda de la Revoluci\u00f3n [Revolution day; statutory holiday]
case 25005:
if (year >= 1910 && year <= 2005) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 20);

}
if (year >= 2006) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);

}
break;

// Transmisi\u00f3n del Poder Ejecutivo Federal [Change of Federal Government; statutory holiday]
case 25006:
if (year >= 1934 && (year - 1934)%6 == 0) {
defined = true;
gc.set(year, Calendar.DECEMBER, 1);

}
break;

// Navidad [Christmas' Day; statutory holiday]
case 25007:
break;

// D\u00eda del Ej\u00e9rcito [Army's Day; civic holiday]
case 25008:
if (year >= 1911) {
defined = true;
gc.set(year, Calendar.DECEMBER, 1);

}
break;

// D\u00eda de la Bandera [Flag Day; civic holiday]
case 25009:
if (year >= 1937) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 24);

}
break;

// Aniversario de la Expropiaci\u00f3n petrolera [Anniversary of the Oil Expropriation; civic holiday]
case 25010:
if (year >= 1938) {
defined = true;
gc.set(year, Calendar.MARCH, 18);

}
break;

// Heroica Defensa de Veracruz [Heroic Defense of Veracruz; civic holiday]
case 25011:
if (year >= 1914) {
defined = true;
gc.set(year, Calendar.APRIL, 21);

}
break;

// Batalla de Puebla [Cinco de Mayo; civic holiday]
case 25012:
if (year >= 1862) {
defined = true;
gc.set(year, Calendar.MAY, 5);

}
break;

// Natalicio de Miguel Hidalgo [Miguel Hidalgo's birthday; civic holiday]
case 25013:
if (year >= 1753) {
defined = true;
gc.set(year, Calendar.MAY, 8);

}
break;

// D\u00eda de la Marina [Marine's Day; civic holiday]
case 25014:
if (year >= 1821) {
defined = true;
gc.set(year, Calendar.JUNE, 1);

}
break;

// D\u00eda de los Ni\u00f1os H\u00e9roes ['Boy Heroes' or 'Heroic Cadets'; civic holiday]
case 25015:
if (year >= 1847) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 13);

}
break;

// Grito de Dolores [Cry of Dolores; civic holiday]
case 25016:
if (year >= 1811) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);

}
break;

// Consumaci\u00f3n de la Independencia [End of Independence War; civic holiday]
case 25017:
if (year >= 1821) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 27);

}
break;

// Natalicio de Jos\u00e9 Ma. Morelos y Pav\u00f3n [Morelos' birthday; civic holiday]
case 25018:
if (year >= 1765) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 30);

}
break;

// Descubrimiento de Am\u00e9rica y D\u00eda de la Raza [Columbus Day; civic holiday]
case 25019:
if (year >= 1492) {
defined = true;
gc.set(year, Calendar.OCTOBER, 12);

}
break;

// D\u00eda de los Santos Reyes [Epiphany; Festivity]
case 25020:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// D\u00eda de San Valent\u00edn [Valentine's Day; Festivity]
case 25021:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// D\u00eda del Ni\u00f1o [Children's Day; Festivity]
case 25022:
defined = true;
gc.set(year, Calendar.APRIL, 30);
break;

// D\u00eda de las Madres [Mother's Day; Festivity]
case 25023:
defined = true;
gc.set(year, Calendar.MAY, 10);
break;

// D\u00eda del Maestro [Teacher's Day; Festivity]
case 25024:
defined = true;
gc.set(year, Calendar.MAY, 15);
break;

// D\u00eda del estudiante [Student's Day; Festivity]
case 25025:
defined = true;
gc.set(year, Calendar.MAY, 23);
break;

// D\u00eda del Padre [Father's Day; Festivity]
case 25026:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
break;

// [All Saint's Day; Festivity]
case 25027:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// [All Soul's Day; Festivity]
case 25028:
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);
break;

// D\u00eda de la Virgen de Guadalupe [Day of the Virgin of Guadalupe; Festivity]
case 25029:
defined = true;
gc.set(year, Calendar.DECEMBER, 12);
break;

// Las Posadas [The Inns; Festivity]
case 25030:
defined = true;
gc.set(year, Calendar.DECEMBER, 16);
break;

// Nochebuena [Christmas Eve; Festivity]
case 25031:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Dia de los Santos Inocentes [Day of the Innocents; Festivity]
case 25032:
defined = true;
gc.set(year, Calendar.DECEMBER, 28);
break;

// [Begin Daylight Saving Time (DST)]
case 25033:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("America/Mexico_City "));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 25034:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("America/Mexico_City "));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// br
// Confraterniza\u00e7\u00e3o Universal [New Year's Day; public holiday]
case 26000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Carnaval [Carnival festivities begin; public holiday]
case 26001:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -40);
break;

// Quarta-feira de Cinzas [Ash Wednesday]
case 26002:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -46);
break;

// Sexta-Feira Santa [Good Friday; public holiday]
case 26003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// P\u00e1scoa
case 26004:
defined = true;
gc.setEaster(year,false);
break;

// Tiradentes [Tiradentes; public holiday]
case 26005:
defined = true;
gc.set(year, Calendar.APRIL, 21);
break;

// Dia do Trabalho [Labour Day; public holiday]
case 26006:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Dia das M\u00e3es [Mother's Day]
case 26007:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// Dia dos Namorados [Valentine's Day]
case 26008:
defined = true;
gc.set(year, Calendar.JUNE, 12);
break;

// [Corpus Christi; public holiday]
case 26009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Dia dos Pais [Father's Day]
case 26010:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);
break;

// Dia do Soldado [Soldier's Day]
case 26011:
if (year >= 1923) {
defined = true;
gc.set(year, Calendar.AUGUST, 25);

}
break;

// Dia da Independ\u00eancia [Independence Day; public holiday]
case 26012:
if (year >= 1825) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 7);

}
break;

// Nossa Senhora de Aparecida [Our Lady of Aparecida; public holiday, catholic]
case 26013:
defined = true;
gc.set(year, Calendar.OCTOBER, 12);
break;

// Dia de Todos-os-Santos
case 26014:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Dia de Finados [All Soul's Day; public holiday]
case 26015:
defined = true;
gc.set(year, Calendar.NOVEMBER, 2);
break;

// Proclama\u00e7\u00e3o da Rep\u00fablica [Proclamation of the Republic Day; public holiday]
case 26016:
defined = true;
gc.set(year, Calendar.NOVEMBER, 15);
break;

// Natal [Christmas Day; public holiday]
case 26017:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// V\u00e9spera de Ano Novo [New Year's Eve]
case 26018:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// ve
// D\u00eda de A\u00f1o Nuevo [New Year's Day; public holiday]
case 27000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// D\u00eda de Reyes [Epiphany; public holiday]
case 27001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
break;

// Carnaval [Carnival; public holiday]
case 27002:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// Semana Santa (Start) [Holy Week Start; public holiday]
case 27003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -7);
break;

// Semana Santa (End) [Holy Week End; public holiday]
case 27004:
defined = true;
gc.setEaster(year,false);
break;

// Jueves Santo [Maundy Thursday]
case 27005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Viernes Santo [Good Friday]
case 27006:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Pascua [Easter Sunday]
case 27007:
defined = true;
gc.setEaster(year,false);
break;

// Lunes de Pascua [Easter Monday]
case 27008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// [Easter Extra Day I]
case 27009:
if (year >= 2010) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);

}
break;

// [Easter Extra Day II]
case 27010:
if (year >= 2010) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 2);

}
break;

// [Easter Extra Day III]
case 27011:
if (year >= 2010) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 3);

}
break;

// D\u00eda de San Jos\u00e9 [Saint Joseph's Day; public holiday]
case 27012:
defined = true;
gc.set(year, Calendar.MARCH, 19);
break;

// 19 de abril [Beginning of the Independence Movement; public holiday]
case 27013:
if (year >= 1810) {
defined = true;
gc.set(year, Calendar.APRIL, 19);

}
break;

// D\u00eda del Trabajador [Labour Day; public holiday]
case 27014:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Batalla de Carabobo [Battle of Carabobo; public holiday]
case 27015:
defined = true;
gc.set(year, Calendar.JUNE, 24);
break;

// 5 de julio [Independence Day; public holiday]
case 27016:
defined = true;
gc.set(year, Calendar.JULY, 5);
break;

// Natalicio del Libertador [Birth of Sim\u00f3n Bol\u00edvar; public holiday]
case 27017:
defined = true;
gc.set(year, Calendar.JULY, 24);
break;

// D\u00eda de la Bandera [Flag Day; public holiday]
case 27018:
if (year >= 1806 && year <= 2005) {
defined = true;
gc.set(year, Calendar.MARCH, 12);

}
if (year >= 2006) {
defined = true;
gc.set(year, Calendar.AUGUST, 3);

}
break;

// D\u00eda de la Raza [Columbus Day; public holiday]
case 27019:
if (year >= 1921 && year <= 2001) {
defined = true;
gc.set(year, Calendar.OCTOBER, 12);

}
break;

// D\u00eda de la Resistencia Ind\u00edgena [Day of Indigenous Resistance; public holiday]
case 27020:
if (year >= 2002) {
defined = true;
gc.set(year, Calendar.OCTOBER, 12);

}
break;

// D\u00eda de Todos los Santos [All Saints Day; public holiday]
case 27021:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
break;

// Feria de la Chinita [Feria of La Chinita; public holiday; only in the Zulian region (3 days)]
case 27022:
defined = true;
gc.set(year, Calendar.NOVEMBER, 17);
break;

// Inmaculada Concepci\u00f3n [Immaculate Conception; public holiday]
case 27023:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Nochebuena [Christmas Eve; public holiday]
case 27024:
defined = true;
gc.set(year, Calendar.DECEMBER, 24);
break;

// Nochevieja [New Year's Eve; public holiday]
case 27025:
defined = true;
gc.set(year, Calendar.DECEMBER, 31);
break;

// [Begin Daylight Saving Time (DST)]
case 27026:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("America/Caracas"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 27027:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("America/Caracas"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// pa
// [New Year's Day; public holiday]
case 28000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// [Martyrs' Day; public holiday]
case 28001:
if (year >= 1964) {
defined = true;
gc.set(year, Calendar.JANUARY, 9);

}
break;

// [Carnival's Monday; public holiday]
case 28002:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -48);
break;

// [Carnival's Tuesday; public holiday]
case 28003:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -47);
break;

// [Holy Friday; public holiday]
case 28004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// [Easter]
case 28005:
defined = true;
gc.setEaster(year,false);
break;

// [May Day; public holiday]
case 28006:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// [Anniversary of the Founding of  Panama City]
case 28007:
if (year >= 1519) {
defined = true;
gc.set(year, Calendar.AUGUST, 15);

}
break;

// [Separation Day (from Colombia); public holiday]
case 28008:
if (year >= 1903) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 3);

}
break;

// [Flag Day; public holiday]
case 28009:
defined = true;
gc.set(year, Calendar.NOVEMBER, 4);
break;

// [Colon Day; public holiday]
case 28010:
defined = true;
gc.set(year, Calendar.NOVEMBER, 5);
break;

// Primer Grito de Independencia de la Villa de los Santos [The uprise in the Villa de los Santos against Spain; public holiday]
case 28011:
if (year >= 1821) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 10);

}
break;

// [Independence Day (from Spain); public holiday]
case 28012:
defined = true;
gc.set(year, Calendar.NOVEMBER, 28);
break;

// [Mothers' Day; public holiday]
case 28013:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// [Christmas; public holiday]
case 28014:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// [Begin Daylight Saving Time (DST)]
case 28015:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("America/Panama"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 28016:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("America/Panama"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// co
// A\u00f1o Nuevo [New Year's Day; public holiday]
case 29000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// D\u00eda de los Reyes Magos [Epiphany; public holiday]
case 29001:
defined = true;
gc.set(year, Calendar.JANUARY, 6);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
gc.add(Calendar.DATE, 6);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
gc.add(Calendar.DATE, 5);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
gc.add(Calendar.DATE, 4);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
gc.add(Calendar.DATE, 3);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
break;

// D\u00eda del Maestro [Teacher's Day; public holiday]
case 29002:
defined = true;
gc.set(year, Calendar.MAY, 15);
break;

// D\u00eda de San Jos\u00e9 [Saint Joseph's Day; public holiday]
case 29003:
defined = true;
gc.set(year, Calendar.MARCH, 19);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
gc.add(Calendar.DATE, 6);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
gc.add(Calendar.DATE, 5);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
gc.add(Calendar.DATE, 4);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
gc.add(Calendar.DATE, 3);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
break;

// [Jueves Santo; public holiday]
case 29004:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -3);
break;

// Viernes Santo [Good Friday; public holiday]
case 29005:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Primero de Mayo [Labor Day; public holiday]
case 29006:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// Ascensi\u00f3n [Ascension; public holiday]
case 29007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);
break;

// [Corpus Christi; public holiday]
case 29008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 60);
break;

// Sagrado Coraz\u00f3n [Sacred Heart; public holiday]
case 29009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 71);
break;

// San Pedro y San Pablo [Saint Peter and Saint Paul; public holiday]
case 29010:
defined = true;
gc.set(year, Calendar.JUNE, 29);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
gc.add(Calendar.DATE, 6);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
gc.add(Calendar.DATE, 5);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
gc.add(Calendar.DATE, 4);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
gc.add(Calendar.DATE, 3);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
break;

// [Independence Day; public holiday]
case 29011:
defined = true;
gc.set(year, Calendar.JULY, 20);
break;

// [Battle of Boyac\u00e1; public holiday]
case 29012:
defined = true;
gc.set(year, Calendar.AUGUST, 7);
break;

// La Asunci\u00f3n [Assumption of Mary; public holiday]
case 29013:
defined = true;
gc.set(year, Calendar.AUGUST, 15);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
gc.add(Calendar.DATE, 6);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
gc.add(Calendar.DATE, 5);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
gc.add(Calendar.DATE, 4);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
gc.add(Calendar.DATE, 3);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
break;

// D\u00eda de la Raza [Columbus Day; public holiday]
case 29014:
defined = true;
gc.set(year, Calendar.OCTOBER, 12);
break;

// [All Saints' Day; public holiday]
case 29015:
defined = true;
gc.set(year, Calendar.NOVEMBER, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
gc.add(Calendar.DATE, 6);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
gc.add(Calendar.DATE, 5);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
gc.add(Calendar.DATE, 4);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
gc.add(Calendar.DATE, 3);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
break;

// [Independence of Cartagena City; public holiday]
case 29016:
defined = true;
gc.set(year, Calendar.NOVEMBER, 11);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
gc.add(Calendar.DATE, 6);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
gc.add(Calendar.DATE, 5);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
gc.add(Calendar.DATE, 4);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
gc.add(Calendar.DATE, 3);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
break;

// La Inmaculada Concepci\u00f3n [Immaculate Conception; public holiday]
case 29017:
defined = true;
gc.set(year, Calendar.DECEMBER, 8);
break;

// Navidad [Christmas Day; public holiday]
case 29018:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// [Begin Daylight Saving Time (DST)]
case 29019:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("America/Bogota"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 29020:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("America/Bogota"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// za
// Uncibijane / Umhla Wonyaka Omtsha / Nuwejaarsdag / Let\u0161at\u0161i la Ngwaga o Moswa / Letsatsi la Ngwaga o Mosha / New Year's Day / Selemo se Setjha / Siku ra Lembe lerintshwa / Lilanga Lemnyaka Lomusha /  / ILanga loNyaka omuTjha [public holiday]
case 30000:
if (year >= 1910 && year <= 1993) {
defined = true;
gc.set(year, Calendar.JANUARY, 1);

}
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.JANUARY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

//  /  /  /  /  / Human Rights Day /  /  /  /  /  [public holiday]
case 30001:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.MARCH, 21);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Good Friday [public holiday]
case 30002:
if (year >= 1910) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);

}
break;

// Easter Sunday
case 30003:
if (year >= 1910) {
defined = true;
gc.setEaster(year,false);

}
break;

// Easter Monday
case 30004:
if (year >= 1910 && year <= 1979) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);

}
break;

// Family Day
case 30005:
if (year >= 1961 && year <= 1974) {
defined = true;
gc.set(year, Calendar.JULY, 10);

}
if (year >= 1980) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);

}
break;

// Van Riebeeck's Day
case 30006:
if (year >= 1952 && year <= 1974) {
defined = true;
gc.set(year, Calendar.APRIL, 6);

}
break;

// Founder's Day
case 30007:
if (year >= 1980 && year <= 1994) {
defined = true;
gc.set(year, Calendar.APRIL, 6);

}
break;

// Freedom Day [public holiday]
case 30008:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.APRIL, 27);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Workers' Day [public holiday]
case 30009:
if (year >= 1990 && year <= 1993) {
defined = true;
gc.set(year, Calendar.MAY, 1);

}
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.MAY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Youth Day [public holiday]
case 30010:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.JUNE, 16);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// National Women's Day [public holiday]
case 30011:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.AUGUST, 9);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Heritage Day [public holiday]
case 30012:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 24);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Ascension Day [public holiday]
case 30013:
if (year >= 1910 && year <= 1993) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 40);

}
break;

// Empire Day [public holiday]
case 30014:
if (year >= 1910 && year <= 1951) {
defined = true;
gc.set(year, Calendar.MAY, 24);

}
break;

// Union Day [public holiday]
case 30015:
if (year >= 1910 && year <= 1960) {
defined = true;
gc.set(year, Calendar.MAY, 31);

}
break;

// Republic Day [public holiday]
case 30016:
if (year >= 1961 && year <= 1993) {
defined = true;
gc.set(year, Calendar.MAY, 31);

}
break;

// Queen's Birthday [public holiday]
case 30017:
if (year >= 1952 && year <= 1960) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JULY,
    year);

}
break;

// King's Birthday [public holiday]
case 30018:
if (year >= 1910 && year <= 1951) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);

}
break;

// Settlers' Day [public holiday]
case 30019:
if (year >= 1952 && year <= 1979) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Kruger Day [public holiday]
case 30020:
if (year >= 1952 && year <= 1993) {
defined = true;
gc.set(year, Calendar.OCTOBER, 10);

}
break;

// Dingaan's Day [public holiday]
case 30021:
if (year >= 1910 && year <= 1951) {
defined = true;
gc.set(year, Calendar.DECEMBER, 16);

}
break;

// Day of the Covenant [public holiday]
case 30022:
if (year >= 1952 && year <= 1979) {
defined = true;
gc.set(year, Calendar.DECEMBER, 16);

}
break;

// Day of the Vow [public holiday]
case 30023:
if (year >= 1979 && year <= 1993) {
defined = true;
gc.set(year, Calendar.DECEMBER, 16);

}
break;

// Day of Reconciliation [public holiday]
case 30024:
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.DECEMBER, 16);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Christmas Day [public holiday]
case 30025:
if (year >= 1910 && year <= 1993) {
defined = true;
gc.set(year, Calendar.DECEMBER, 25);

}
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Boxing Day [public holiday]
case 30026:
if (year >= 1910 && year <= 1979) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);

}
break;

// Day of Goodwill [public holiday]
case 30027:
if (year >= 1980 && year <= 1993) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);

}
if (year >= 1994) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}

}
break;

// Begin Daylight Saving Time (DST)
case 30028:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Africa/Johannesburg"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// End Daylight Saving Time (DST)
case 30029:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Africa/Johannesburg"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// cn
// \u5143\u65e6 [New Year's Day; public]
case 31000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// \u6625\u8282 (Eve) [Spring Festival (Chinese New Year's Eve); public]
case 31001:
if (year >= 2008 && year <= 2013) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, -1);

}
break;

// \u6625\u8282 (1st day) [Spring Festival (Chinese New Year, 1st day); public]
case 31002:
if (year >= 1950) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));

}
break;

// \u6625\u8282 (2nd day) [Spring Festival (Chinese New Year, 2nd day); public]
case 31003:
if (year >= 1950) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, 1);

}
break;

// \u6625\u8282 (3rd day) [Spring Festival (Chinese New Year, 3rd day); public]
case 31004:
if (year >= 1950 && year <= 2007) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, 2);

}
if (year >= 2008 && year <= 2013) {
defined = true;
return NOT_DEFINED;
}
if (year >= 2014) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, 2);

}
break;

// \u6e05\u660e\u8282 [Tomb-Sweeping Day (Qingming Festival); public]
case 31005:
if (year >= 2008) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
gc.add(Calendar.DATE, 15);

}
break;

// \u6e05\u660e\u8282 [Labour Day; public]
case 31006:
if (year >= 1950) {
defined = true;
gc.set(year, Calendar.MAY, 1);

}
break;

// \u6e05\u660e\u8282 (2nd day) [Labour Day (2nd day); public]
case 31007:
if (year >= 2000 && year <= 2007) {
defined = true;
gc.set(year, Calendar.MAY, 2);

}
break;

// \u6e05\u660e\u8282 (3rd day) [Labour Day (3rd day); public]
case 31008:
if (year >= 2000 && year <= 2007) {
defined = true;
gc.set(year, Calendar.MAY, 3);

}
break;

// \u7aef\u5348\u8282 [Dragon Boat Festival; public]
case 31009:
if (year >= 2008) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 5));
gc.add(Calendar.DATE, 4);

}
break;

// \u4e2d\u79cb\u8282 [Mid-Autumn Festival; public]
case 31010:
if (year >= 2008) {
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 8));
gc.add(Calendar.DATE, 14);

}
break;

// \u56fd\u5e86\u8282 [National Day (1st day); public]
case 31011:
if (year >= 1950) {
defined = true;
gc.set(year, Calendar.OCTOBER, 1);

}
break;

// \u56fd\u5e86\u8282 (2nd day) [National Day (2nd day); public]
case 31012:
if (year >= 1950) {
defined = true;
gc.set(year, Calendar.OCTOBER, 2);

}
break;

// \u56fd\u5e86\u8282 (3rd day) [National Day (3rd day); public]
case 31013:
if (year >= 1999) {
defined = true;
gc.set(year, Calendar.OCTOBER, 3);

}
break;

// \u5143\u65e6 [New Year's Day; traditional]
case 31014:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// \u6625\u8282 [Chinese New Year's Eve; traditional]
case 31015:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, -1);
break;

// \u6625\u8282 [Chinese New Year (Spring Festival); traditional]
case 31016:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
break;

// \u5143\u5bb5\u8282 [Lantern Festival; traditional]
case 31017:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, 14);
break;

// \u4e2d\u548c\u8282 [Zhonghe Festival; traditional]
case 31018:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 2));
gc.add(Calendar.DATE, 1);
break;

// \u4e0a\u5df3\u8282 [Double Third Festival (Shangsi Festival); traditional]
case 31019:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 3));
gc.add(Calendar.DATE, 2);
break;

// \u56fd\u9645\u5987\u5973\u8282 [International Women's Day; traditional; public holiday applicable to women (half-day)]
case 31020:
defined = true;
gc.set(year, Calendar.MARCH, 8);
break;

// \u690d\u6811\u8282 [Arbor Day; traditional]
case 31021:
defined = true;
gc.set(year, Calendar.MARCH, 12);
break;

// \u6e05\u660e\u8282 [Tomb-Sweeping Day (Qingming Festival); traditional]
case 31022:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
gc.add(Calendar.DATE, 15);
break;

// \u52b3\u52a8\u8282 [Labour Day; traditional]
case 31023:
defined = true;
gc.set(year, Calendar.MAY, 1);
break;

// \u9752\u5e74\u8282 [Youth Day; traditional; public holiday applicable to youth from the age of 14 to 28 (half-day)]
case 31024:
defined = true;
gc.set(year, Calendar.MAY, 4);
break;

// \u516d\u4e00\u513f\u7ae5\u8282 [Children's Day; traditional; public holiday applicable to children below the age of 14 (1 day)]
case 31025:
defined = true;
gc.set(year, Calendar.JUNE, 1);
break;

// \u7aef\u5348\u8282 [Dragon Boat Festival; traditional]
case 31026:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 5));
gc.add(Calendar.DATE, 4);
break;

// \u5efa\u515a\u8282 [CPC Founding Day; traditional]
case 31027:
if (year >= 1921) {
defined = true;
gc.set(year, Calendar.JULY, 1);

}
break;

// \u4e2d\u56fd\u822a\u6d77\u65e5 [China National Maritime Day; traditional]
case 31028:
if (year == 2005) {
defined = true;
gc.set(year, Calendar.JULY, 11);

}
break;

// \u5efa\u519b\u8282 [Army Day; traditional; public holiday for military personnel in active service (half-day)]
case 31029:
defined = true;
gc.set(year, Calendar.AUGUST, 1);
break;

// \u4e03\u5915 [Double Seven Festival (Qixi Festival); traditional]
case 31030:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 7));
gc.add(Calendar.DATE, 6);
break;

// \u4e2d\u5143\u8282 [Spirit Festival (Ghost Festival); traditional]
case 31031:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 7));
gc.add(Calendar.DATE, 14);
break;

// \u4e2d\u79cb\u8282 [Mid-Autumn Festival (Moon Festival); traditional]
case 31032:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 8));
gc.add(Calendar.DATE, 14);
break;

// \u4e2d\u56fd\u4eba\u6c11\u6297\u65e5\u6218\u4e89\u80dc\u5229\u7eaa\u5ff5\u65e5 [Victory over Japan Day (V-J Day); traditional]
case 31033:
if (year >= 2014) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 3);

}
break;

// \u70c8\u58eb\u7eaa\u5ff5\u65e5 [National Memorial Day; traditional]
case 31034:
if (year >= 2014) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 30);

}
break;

// \u56fd\u5e86\u8282 [National Day; traditional]
case 31035:
if (year >= 2014) {
defined = true;
gc.set(year, Calendar.OCTOBER, 1);

}
break;

// \u91cd\u967d\u7bc0 / \u91cd\u9633\u8282 [Double Ninth Festival (Chongyang Festival); traditional]
case 31036:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 9));
gc.add(Calendar.DATE, 8);
break;

// \u4e0b\u5143\u8282 [Spirit Festival (Water Lantern Festival); traditional]
case 31037:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 10));
gc.add(Calendar.DATE, 14);
break;

// \u814a\u516b\u8282 [Laba Festival; traditional]
case 31038:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 12));
gc.add(Calendar.DATE, 7);
break;

// \u5357\u4eac\u5927\u5c60\u6740\u6b7b\u96be\u8005\u56fd\u5bb6\u516c\u796d\u65e5 [Nanking Massacre Memorial Day; traditional]
case 31039:
if (year >= 2014) {
defined = true;
gc.set(year, Calendar.DECEMBER, 13);

}
break;

// [March Equinox]
case 31040:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [September Equinox]
case 31041:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [June Solstice]
case 31042:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [December Solstice]
case 31043:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// tw
// \u5143\u65e6 [New Year's Day; public]
case 32000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// \u8fb2\u66c6\u9664\u5915 [Lunar New Year's Eve; public]
case 32001:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, -1);
break;

// \u6625\u8282 (1st day) [Spring Festival (Lunar New Year, 1st day); public]
case 32002:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
break;

// \u6625\u8282 (2nd day) [Spring Festival (Lunar New Year, 2nd day); public]
case 32003:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, 1);
break;

// \u6625\u8282 (3rd day) [Spring Festival (Lunar New Year, 3rd day); public]
case 32004:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 1));
gc.add(Calendar.DATE, 2);
break;

// 228\u548c\u5e73\u7d00\u5ff5\u65e5 [Peace Memorial Day; public]
case 32005:
defined = true;
gc.set(year, Calendar.FEBRUARY, 28);
break;

// \u5a66\u5973\u7bc0\u3001\u5152\u7ae5\u7bc0\u5408\u5002 [The Combined Holidays of Women's Day and Children's Day; public]
case 32006:
defined = true;
gc.set(year, Calendar.APRIL, 4);
break;

// \u6e05\u660e\u8282 [Tomb-Sweeping Day (Qingming Festival); public]
case 32007:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
gc.add(Calendar.DATE, 15);
break;

// \u7aef\u5348\u8282 [Dragon Boat Festival; public]
case 32008:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 5));
gc.add(Calendar.DATE, 4);
break;

// \u4e2d\u79cb\u8282 [Mid-Autumn Festival; public]
case 32009:
defined = true;
gc.set(ChineseCalendarCalculations.chineseLunisolarStartDate(year, 8));
gc.add(Calendar.DATE, 14);
break;

// \u96d9\u5341\u7bc0 [National Day/Double Tenth Day; public]
case 32010:
defined = true;
gc.set(year, Calendar.OCTOBER, 10);
break;

// [March Equinox]
case 32011:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [June Solstice]
case 32012:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [September Equinox]
case 32013:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [December Solstice]
case 32014:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [Begin Daylight Saving Time (DST)]
case 32015:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Asia/Taipei"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 32016:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Asia/Taipei"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// jp
// Ganjitsu [New Year's Day; national holiday]
case 33000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Seijin no hi [Coming of Age Day; national holiday]
case 33001:
if (year >= 1948 && year <= 1999) {
defined = true;
gc.set(year, Calendar.JANUARY, 15);

}
if (year >= 2000) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JANUARY,
    year);

}
break;

// setsubun [Setsubun]
case 33002:
defined = true;
gc.set(year, Calendar.FEBRUARY, 3);
break;

// Kigen-setsu
case 33003:
if (year >= 1872 && year <= 1948) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 11);

}
break;

// Kenkoku kinen no hi [National Foundation Day; national holiday]
case 33004:
if (year >= 1967) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 11);

}
break;

// [Valentine's Day]
case 33005:
defined = true;
gc.set(year, Calendar.FEBRUARY, 14);
break;

// hina matsuri [Doll's Festival; national holiday]
case 33006:
defined = true;
gc.set(year, Calendar.MARCH, 3);
break;

// hina matsuri [White Day; national holiday]
case 33007:
defined = true;
gc.set(year, Calendar.MARCH, 14);
break;

// Shunki k\u014drei-sai
case 33008:
if (year <= 1947) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Shunbun no hi [Vernal equinox; national holiday]
case 33009:
if (year >= 1948) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Sh\u014dwa no hi [Sh\u014dwa Day; national holiday]
case 33010:
if (year >= 2007) {
defined = true;
gc.set(year, Calendar.APRIL, 29);

}
break;

// Kenp\u014d kinenbi [Constitution Memorial Day; national holiday]
case 33011:
if (year >= 1948) {
defined = true;
gc.set(year, Calendar.MAY, 3);

}
break;

// Kokumin no ky\u016bjitsu
case 33012:
if (year >= 1986 && year <= 2006) {
defined = true;
gc.set(year, Calendar.MAY, 4);

}
break;

// Midori no hi [Greenery Day; national holiday]
case 33013:
if (year >= 1989 && year <= 2006) {
defined = true;
gc.set(year, Calendar.APRIL, 29);

}
if (year >= 2007) {
defined = true;
gc.set(year, Calendar.MAY, 4);

}
break;

// Kodomo no hi [Children's Day; national holiday]
case 33014:
defined = true;
gc.set(year, Calendar.MAY, 5);
break;

// Umi no hi [Marine Day; national holiday]
case 33015:
if (year >= 1996 && year <= 2002) {
defined = true;
gc.set(year, Calendar.JULY, 20);

}
if (year >= 2003) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JULY,
    year);

}
break;

// Keir\u014d no hi [Respect-for-the-Aged Day; national holiday]
case 33016:
if (year >= 1966 && year <= 2002) {
defined = true;
gc.set(year, Calendar.SEPTEMBER, 15);

}
if (year >= 2003) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.THIRD,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);

}
break;

// Sh\u016bki k\u014drei-sai
case 33017:
if (year <= 1947) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Sh\u016bbun no hi [Autumnal equinox; national holiday]
case 33018:
if (year >= 1948) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Taiiku no hi [Health and Sports Day; national holiday]
case 33019:
if (year >= 1966 && year <= 1999) {
defined = true;
gc.set(year, Calendar.OCTOBER, 10);

}
if (year >= 2000) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);

}
break;

// Meiji-setsu [Meiji-setsu; national holiday]
case 33020:
if (year >= 1867 && year <= 1947) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 3);

}
break;

// Bunka no hi [Culture Day; national holiday]
case 33021:
if (year >= 1948) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 3);

}
break;

// Niiname-sai [Niiname-sai; festival]
case 33022:
if (year <= 1948) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 23);

}
break;

// Kinr\u014d kansha no hi [Labor Thanksgiving Day; national holiday]
case 33023:
if (year >= 1948) {
defined = true;
gc.set(year, Calendar.NOVEMBER, 23);

}
break;

// Tench\u014dsetsu
case 33024:
if (year >= 1868 && year <= 1872) {
defined = true;
return UNKNOWN;

}
if (year >= 1873 && year <= 1947) {
defined = true;
gc.set(year, Calendar.APRIL, 29);

}
break;

// Tenn\u014d tanj\u014dbi [The Emperor's Birthday; national holiday]
case 33025:
if (year >= 1948 && year <= 1989) {
defined = true;
gc.set(year, Calendar.APRIL, 29);

}
if (year >= 1990 && year <= 1918) {
defined = true;
gc.set(year, Calendar.DECEMBER, 23);

}
if (year >= 1920) {
defined = true;
gc.set(year, Calendar.FEBRUARY, 23);

}
break;

// [March Equinox; astronomical event]
case 33026:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [June Solstice; astronomical event]
case 33027:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [September Equinox; astronomical event]
case 33028:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// [December Solstice; astronomical event]
case 33029:
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}
break;

// Shunki k\u014dreisai [Vernal Imperial Rituals]
case 33030:
if (year >= 1878 && year <= 1947) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Shunbun no Hi [Vernal Equinox Day]
case 33031:
if (year >= 1948) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Sh\u016bki k\u014drei-sai [Autumn Imperial Rituals]
case 33032:
if (year >= 1878 && year <= 1947) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// Sh\u016bbun no Hi [Autumnal Equinox Day]
case 33033:
if (year >= 1948) {
defined = true;
try {
gc.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
gc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
} catch (IllegalArgumentException e) {
   throw e;
}

}
break;

// [Begin Daylight Saving Time (DST)]
case 33034:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Asia/Tokyo"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// [End Daylight Saving Time (DST)]
case 33035:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Asia/Tokyo"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// au
// New Year [public holiday]
case 34000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
break;

// Australia Day [public holiday]
case 34001:
if (year >= 1788) {
defined = true;
gc.set(year, Calendar.JANUARY, 26);

}
break;

// Royal Hobart Regatta [publich holiday, regional; Tasmania, Hobart area only]
case 34002:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.FEBRUARY,
    year);
break;

// Labour Day [publich holiday, regional; Western Australia only]
case 34003:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Adelaide Cup [publich holiday, regional; South Australia]
case 34004:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Canberra Day [publich holiday, regional; Australian Capital Territory]
case 34005:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Eight Hours Day [publich holiday, regional; Tasmania]
case 34006:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Labour Day [public holiday, regional; Victoria]
case 34007:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Good Friday [public holiday]
case 34008:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Easter Saturday (Holy Saturday) [public holiday]
case 34009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -1);
break;

// Easter Sunday [public holiday]
case 34010:
defined = true;
gc.setEaster(year,false);
break;

// Easter Monday [public holiday]
case 34011:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Easter Monday [public holiday, regional; Tasmania]
case 34012:
break;

// Anzac Day [public holiday]
case 34013:
if (year >= 1916) {
defined = true;
gc.set(year, Calendar.APRIL, 25);

}
break;

// Labour Day [public holiday, regional; Queensland only]
case 34014:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// May Day [publich holiday, regional; Northern Territory only]
case 34015:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MAY,
    year);
break;

// Mother's Day
case 34016:
if (year >= 1924) {
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.SUNDAY,
    ExtendedGregorianCalendar.MAY,
    year);

}
break;

// Foundation Day [publich holiday, regional; Western Australia only]
case 34017:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
break;

// Queen's Birthday [publich holiday, regional; all exept Western Australia]
case 34018:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
break;

// Picnic Day [publich holiday, regional; Northern Territory]
case 34019:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.AUGUST,
    year);
break;

// Labour Day [public holiday, regional; ACT, NSW, SA]
case 34020:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
break;

// Recreation Day [public holiday, regional; Tasmania, Not Hobart area]
case 34021:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
break;

// Family & Community Day [public holiday, regional; ACT]
case 34022:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.TUESDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
break;

// Melbourne Cup Day [publich holiday, regional; Victoria only]
case 34023:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.TUESDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
break;

// Christmas Day [public holiday]
case 34024:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
break;

// Boxing Day [public holiday; all, except South Australia]
case 34025:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// Proclamation Day [public holiday; South Australia only]
case 34026:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
break;

// nz
// New Year's Day [Statutory holiday]
case 35000:
defined = true;
gc.set(year, Calendar.JANUARY, 1);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// Day after New Year's Day [Statutory holiday]
case 35001:
defined = true;
gc.set(year, Calendar.JANUARY, 2);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// Waitangi Day [Statutory holiday]
case 35002:
defined = true;
gc.set(year, Calendar.FEBRUARY, 6);
break;

// Wellington Anniversary Day [Provincial Anniversary Day; Wellington Province]
case 35003:
defined = true;
gc.set(year, Calendar.JANUARY, 22);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Auckland Anniversary Day [Provincial Anniversary Day; Auckland Province]
case 35004:
defined = true;
gc.set(year, Calendar.JANUARY, 29);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Northland Anniversary Day [Provincial Anniversary Day; Northland]
case 35005:
defined = true;
gc.set(year, Calendar.JANUARY, 29);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Nelson Anniversary Day [Provincial Anniversary Day; Nelson]
case 35006:
defined = true;
gc.set(year, Calendar.FEBRUARY, 1);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Good Friday [Statutory holiday]
case 35007:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);
break;

// Easter Sunday
case 35008:
defined = true;
gc.setEaster(year,false);
break;

// Easter Monday [Statutory holiday]
case 35009:
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);
break;

// Otago Anniversary Day [Provincial Anniversary Day; Otago Province]
case 35010:
defined = true;
gc.set(year, Calendar.MARCH, 23);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Taranaki Anniversary Day [Provincial Anniversary Day; New Plymouth]
case 35011:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.SECOND,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.MARCH,
    year);
break;

// Anzac Day [Statutory holiday]
case 35012:
if (year >= 1916) {
defined = true;
gc.set(year, Calendar.APRIL, 25);

}
break;

// Queen's Birthday [Statutory holiday]
case 35013:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.JUNE,
    year);
break;

// South Canterbury Anniversary Day [Provincial Anniversary Day; South Canterbury]
case 35014:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.SEPTEMBER,
    year);
break;

// Hawke's Bay Anniversary Day [Provincial Anniversary Day; Hawke's Bay]
case 35015:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
gc.add(Calendar.DATE, -3);
break;

// Labour Day [Statutory holiday]
case 35016:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
break;

// Marlborough Anniversary Day [Provincial Anniversary Day; Marlborough]
case 35017:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FOURTH,
    ExtendedGregorianCalendar.MONDAY,
    ExtendedGregorianCalendar.OCTOBER,
    year);
gc.add(Calendar.DATE, 7);
break;

// Christchurch Show Day [Provincial Anniversary Day; Northern and Central Canterbury]
case 35018:
defined = true;
gc.set(year, Calendar.NOVEMBER, 16);
break;

// Canterbury Anniversary Day [Provincial Anniversary Day; Christchurch City]
case 35019:
defined = true;
gc.setNthDow(
    ExtendedGregorianCalendar.FIRST,
    ExtendedGregorianCalendar.TUESDAY,
    ExtendedGregorianCalendar.NOVEMBER,
    year);
gc.add(Calendar.DATE, 10);
break;

// Chatham Islands Anniversary Day [Provincial Anniversary Day; Chatham Islands]
case 35020:
defined = true;
gc.set(year, Calendar.NOVEMBER, 30);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Westland Anniversary Day [Provincial Anniversary Day; Greymouth]
case 35021:
defined = true;
gc.set(year, Calendar.DECEMBER, 1);
gc.setNearestWeekday(Calendar.MONDAY);
break;

// Christmas Day [Statutory holiday]
case 35022:
defined = true;
gc.set(year, Calendar.DECEMBER, 25);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 1);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// Boxing Day [Statutory holiday]
case 35023:
defined = true;
gc.set(year, Calendar.DECEMBER, 26);
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
gc.add(Calendar.DATE, 2);

}
if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
gc.add(Calendar.DATE, 2);

}
break;

// Begin Daylight Saving Time (DST)
case 35024:
defined = true;
try {
    gc.setDSTstart(year, TimeZone.getTimeZone("Pacific/Auckland"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// End Daylight Saving Time (DST)
case 35025:
defined = true;
try {
    gc.setDSTend(year, TimeZone.getTimeZone("Pacific/Auckland"));
} catch (Exception e) {
    return NOT_DEFINED;
}
break;

// ddr
// Neujahr [New Year's Day; public holiday]
case 36000:
if (year >= 1950 && year <= 1990) {
defined = true;
gc.set(year, Calendar.JANUARY, 1);

}
break;

// Karfreitag [Good Friday; public holiday]
case 36001:
if (year >= 1950 && year <= 1990) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, -2);

}
break;

// Ostersonntag (Ostern) [Easter Sunday]
case 36002:
if (year >= 1950 && year <= 1990) {
defined = true;
gc.setEaster(year,false);

}
break;

// Ostermontag
case 36003:
if (year >= 1950 && year <= 1967) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);

}
if (year == 1990) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 1);

}
break;

// Internationaler Kampf- und Feiertag der Werkt\u00e4tigen f\u00fcr Frieden und Sozialismus
case 36004:
if (year >= 1950 && year <= 1990) {
defined = true;
gc.set(year, Calendar.MAY, 1);

}
break;

// Tag der Befreiung
case 36005:
if (year >= 1950 && year <= 1967) {
defined = true;
gc.set(year, Calendar.MAY, 8);

}
if (year == 1985) {
defined = true;
gc.set(year, Calendar.MAY, 8);

}
break;

// Tag des Sieges
case 36006:
if (year == 1975) {
defined = true;
gc.set(year, Calendar.MAY, 9);

}
break;

// Christi Himmelfahrt
case 36007:
if (year >= 1950 && year <= 1967) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);

}
if (year == 1990) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 39);

}
break;

// Pfingstsonntag (Pfingsten)
case 36008:
if (year >= 1950 && year <= 1990) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 49);

}
break;

// Pfingstmontag
case 36009:
if (year >= 1950 && year <= 1990) {
defined = true;
gc.setEaster(year,false);
gc.add(Calendar.DATE, 50);

}
break;

// Tag der Republik
case 36010:
if (year >= 1949 && year <= 1989) {
defined = true;
gc.set(year, Calendar.OCTOBER, 7);

}
break;

// Reformationstag
case 36011:
if (year >= 1950 && year <= 1966) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
if (year == 1990) {
defined = true;
gc.set(year, Calendar.OCTOBER, 31);

}
break;

// Bu\u00df- und Bettag [Day of Repentance and Prayer; public holiday]
case 36012:
if (year >= 1950 && year <= 1966) {
defined = true;
gc.setAdvent(year);
gc.add(Calendar.DATE, -11);

}
break;

// 1. Weihnachtsfeiertag (Weihnachten) [Christmas Day; public holiday]
case 36013:
if (year >= 1949 && year <= 1989) {
defined = true;
gc.set(year, Calendar.DECEMBER, 25);

}
break;

// 2. Weihnachtsfeiertag (Weihnachten) [Boxing Day; public holiday]
case 36014:
if (year >= 1949 && year <= 1989) {
defined = true;
gc.set(year, Calendar.DECEMBER, 26);

}
break;



            default: return "Internal error: No holiday definition for id " + tid + " found.";
        }
		if (!defined) return NOT_DEFINED;
		df.setCalendar(gc);
        return df.format(gc.getTime());
    }

    @Override
    public String getInitialValue() {
       // return the current year
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
       return formatter.format(new Date());
    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        int value = Integer.parseInt(input);
        int add = Integer.parseInt(plus.toPlainString());
        value += add;
        setInput(Integer.toString(value));
        return getInput();
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        setInput(big.toPlainString());
        return getInput();
    }

    @Override
    public String getCard() {
        return "dateCard";
    }

    @Override
    public String getTransferValue(int s) throws Exception {
        return input;
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return input;
    }
	
}

