package pl.polsl.tomasz.walorski.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pl.polsl.tomasz.walorski.model.Footballer;
/**
 * Class include methods ensuring, that input has proper type for Footballer Servlet.
 * @author Tomasz Walorski
 * @version 1.1
 */
public class FootballerController {
    /**
     * To this footballer object method of this class set proper inputs.
     */
    Footballer footballer;
    /**
     * Constructor of classes.
     * @param footballer Footballer object to set.
     */
    public FootballerController(Footballer footballer){
        this.footballer = footballer;
    }
    /**
     * Method check if each input is correct for updating footballer.
     * @param id Id of footballer object to update.
     * @param fullName Input string to update fullName field.
     * @param clubNumber Input string to update clubNumber field.
     * @param birthDate Input string to update birthDate field.
     * @param playingPosition Input string to update playingPosition field.
     * @param clubId Id of club to update clubId field.
     * @return Method return error's messages list, if inputs have not proper type.
     */
    public ArrayList<String> isInputCorrectForUpdate(String id, String fullName, String clubNumber, String birthDate, String playingPosition, String clubId){
        ArrayList <String> errors = new ArrayList<String>();
        if(footballer!=null){
            if(id.isEmpty() || (fullName.isEmpty() && clubNumber.isEmpty() && birthDate.isEmpty() && playingPosition.isEmpty() && clubId.isEmpty())){
                errors.add("Id and at least one field must be filled.");
            } else {
                if(isInteger(id, "id")!=null){
                    errors.add(isInteger(id, "id"));
                }
                if(!fullName.isEmpty()){
                    footballer.setFullName(fullName);
                }
                if(!playingPosition.isEmpty()){
                    footballer.setPlayingPosition(playingPosition);
                }
                if(!birthDate.isEmpty()){
                    if(isDate(birthDate) != null){
                        errors.add(isDate(birthDate));
                    }
                }
                if(!clubNumber.isEmpty()){
                    if(isInteger(clubNumber, "clubNumber")!=null){
                        errors.add(isInteger(clubNumber, "clubNumber"));
                    }else footballer.setClubNumber(Integer.parseInt(clubNumber));
                }
                if(!clubId.isEmpty()){
                    if(isInteger(clubId, "clubId")!=null){
                        errors.add(isInteger(clubId, "clubId"));
                    }
                }
            }    
        } else {
            errors.add("Club's object is null.");
        }
        return errors;
    }
    /**
     * Method check if each input is correct for creating footballer.
     * @param fullName Input string to set in fullName field.
     * @param clubNumber Input string to set in clubNumber field.
     * @param birthDate Input string to set in birthDate field.
     * @param playingPosition Input string to set in playingPosition field.
     * @param clubId Id of club to set in clubId field.
     * @return Method return error's messages list, if inputs have not proper type.
     */
    public ArrayList<String> isInputCorrectForCreate(String fullName, String clubNumber, String birthDate, String playingPosition, String clubId){
        ArrayList <String> errors = new ArrayList<String>();
        if(footballer!=null){
            if(fullName.isEmpty() || clubNumber.isEmpty() || birthDate.isEmpty() || playingPosition.isEmpty() || clubId.isEmpty()){
                errors.add("Each field must be filled.");
            } else {
                footballer.setFullName(fullName);
                if(isInteger(clubNumber, "clubNumber")!=null){
                    errors.add(isInteger(clubNumber, "clubNumber"));
                }else footballer.setClubNumber(Integer.parseInt(clubNumber));
                footballer.setPlayingPosition(playingPosition);
                if(isDate(birthDate) != null){
                    errors.add(isDate(birthDate));
                }
            }   
        } else {
            errors.add("Club's object is null.");
        }
        return errors;
    }
    /**
     * Method check, if inputed string is integer.
     * @param input String object entered to check.
     * @param field Name of field which is setted.
     * @return Method return error's message, if input has not proper type.
     */
    public String isInteger(String input, String field){
        try{
            Integer.parseInt(input);
        } catch(NumberFormatException e){
            return field + " must be integer.";
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
            footballer.setBirthDate(format.parse(input));
        } catch(ParseException e){
            return "Wrong date format";
        } 
        return null;
    }
}
