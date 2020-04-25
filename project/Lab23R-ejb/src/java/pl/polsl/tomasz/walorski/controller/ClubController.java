package pl.polsl.tomasz.walorski.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pl.polsl.tomasz.walorski.model.Club;
/**
 * Class include methods ensuring, that input has proper type for Club Servlet.
 * @author Tomasz Walorski
 * @version 1.1
 */
public class ClubController {
    /**
     * To this club object method of this class set proper inputs.
     */
    Club club;
    /**
     * Constructor of classes.
     * @param club Club object to set.
     */
    public ClubController(Club club){
        this.club = club;
    }
    /**
     * Method check if each input is correct for updating club.
     * @param id Id of club object to update.
     * @param clubFullName Input string to update clubFullName field.
     * @param clubShortName Input string to update clubShortName field.
     * @param founded Input string to update founded field.
     * @param city Input string to update city field.
     * @return Method return error's messages list, if inputs have not proper type.
     */
    public ArrayList<String> isInputCorrectForUpdate(String id, String clubFullName, String clubShortName, String founded, String city){
        ArrayList <String> errors = new ArrayList<String>();
        if(club!=null){
            if(id.isEmpty() || (clubFullName.isEmpty() && clubShortName.isEmpty() && founded.isEmpty() && city.isEmpty())){
                errors.add("Id and at least one field must be filled.");
            } else {
                if(isInteger(id)!=null){
                    errors.add(isInteger(id));
                }
                if(!clubFullName.isEmpty()){
                    club.setClubFullName(clubFullName);
                }
                if(!clubShortName.isEmpty()){
                    club.setClubShortName(clubShortName);
                }
                if(!founded.isEmpty()){
                    if(isDate(founded) != null){
                        errors.add(isDate(founded));
                    }
                }
                if(!city.isEmpty()){
                    club.setCity(city);
                }
            }
        } else {
            errors.add("Club's object is null.");
        }
        return errors;
    }
    /**
     * Method check if each input is correct for creating club.
     * @param clubFullName Input string to update clubFullName field.
     * @param clubShortName Input string to update clubShortName field.
     * @param founded Input string to update founded field.
     * @param city Input string to update city field.
     * @return Method return error's messages list, if inputs have not proper type.
     */
    public ArrayList<String> isInputCorrectForCreate(String clubFullName, String clubShortName, String founded, String city){
        ArrayList <String> errors = new ArrayList<String>();
        if(club!=null){
            if(clubFullName.isEmpty() || clubShortName.isEmpty() || founded.isEmpty() || city.isEmpty()){
                errors.add("Each field must be filled.");
            } else {
                club.setClubFullName(clubFullName);
                club.setClubShortName(clubShortName);
                if(isDate(founded) != null){
                    errors.add(isDate(founded));
                }
                club.setCity(city);
            }    
        } else {
            errors.add("Club's object is null.");
        }
        return errors;
    }
    /**
     * Method check, if inputed string is integer.
     * @param input String object entered to check.
     * @return Method return error's message, if input has not proper type.
     */
    public String isInteger(String input){
        try{
            Integer.parseInt(input);
        } catch(NumberFormatException e){
            return "Id must be integer.";
        }
        return null;
    }
    /**
     * Method check if if inputed string is data.
     * @param input String object entered to check.
     * @return  Method return error's message, if input has not proper type.
     */
    public String isDate(String input){
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            format.setLenient(false);
            format.parse(input);
            club.setFounded(format.parse(input));
        } catch(ParseException e){
            return "Wrong date format";
        } 
        return null;
    }
}
