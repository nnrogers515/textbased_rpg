import java.util.Map;
import java.util.HashMap;

public class HELP_COMMAND implements Command {

    //The full command entered by the user.
    String input;
    //The list of commands in the game.
    Map commands = new HashMap();
    boolean tooltips;
    
    HELP_COMMAND(String cmd, boolean tt){
        commands.put("STATUS", 0);
        commands.put("QUEST", 1);
        commands.put("HELP", 2);
        commands.put("EXIT", 3);
        commands.put("INVENTORY", 4);
        commands.put("STANCE", 5);
        commands.put("CAST", 6);
        commands.put("LOCK", 7);
        commands.put("NPC", 8);
        commands.put("PLAYER", 9);
        input = cmd;
        tooltips = tt;
    }
    
    @Override
    public String printOutput(){
        //See if the user just typed "HELP" or "HELP [command]"
        if (input.split(" ").length > 1){
            
            //Isolate the specific help command entered.
            String command = input.split(" ")[1];
            
            //Check if the command entered is a sub-command of HELP
            if (command.equalsIgnoreCase("TOOLTIPS")){
                return toggleTooltips();
            }else if (command.equalsIgnoreCase("LIST")){
                return listCommands();
            }
            
            //Processes sub commands if not list or tooltips
            return processCommand(command.toUpperCase());
            
        }else{
            return helpList();
        }
    }
    
    //This tells the player how to use the HELP command to find information on the game.
    private String helpList(){
        String help = "How to use HELP:\nUse HELP with another parameter to learn more about that command.\n"
                + "HELP LIST lists all of the commands the player can use.\n"
                + "HELP TOOLTIPS toggles the tooltips show/hide.\n"
                + "HELP [command] gives you help for that specific command.";
        return help;
    }
    
    private String processCommand(String subCommand){
        
        //Get the name of the command entered sop it can be used more easily later.
        String help;
        //Creates a key to use with the switch
        int subCommandIndex = 25;
        //Checks to see if the sub command exist then sets the map value of that command
        if(commands.containsKey(subCommand))
            subCommandIndex = (int)(commands.get(subCommand));
        //This prints the usage directions on the specified command
        //NOTE: These descriptions will get more detailed once more of the game is done.
        switch(subCommandIndex){
            case 0://Status
                help = "STATUS prints some statistics about the player for them to read.\n" + 
                        "This includes their HP, MP, Active Quest, and Level.\n";
                return help;
            case 1://Quest log
                help = "QUEST tells the player about their current quest.\n"
                        + "QUEST commands include\n"
                        + "QUEST LIST lists all of the current quest you have in your log.\n"
                        + "QUEST COMPLETED lists all of the quest you have completed.\n"
                        + "QUEST [quest name] outputs the details about a given quest.\n";
                return help;
            case 2://Help
                return helpList();
            case 3://Exit game
                help = "EXIT exits the game, after prompting the player if they're sure.\n";
                return help;
            case 4://Inventory
                help = "INVENTORY displays the contents of the player's inventory, and lets them manage it.\n"
                        + "INVENTORY commands include\n"
                        + "INVENTORY LIST lists off all of the current items in your inventory.\n"
                        + "INVENTORY VIEW [item name] outpus the details about a given item.\n"
                        + "INVENTORY USE [item name] allows you to use an item if it is useable.\n"
                        + "INVENTORY DROP [item name] allows you to drop an item from your inventory.\n"
                        + "INVENTORY EQUIP [item name] [slot id] allows you to equip an item that can be equipped.\n";
                return help;
            case 5://Stance
                help = "STANCE lets the player change their stance between ATTACK and DEFENSE.\n"
                        + "STANCE commands include\n"
                        + "STANCE ATT switches your stance into attact stance.\n"
                        + "STANCE DEF switches your stance into defense stance.\n"
                        + "STANCE STATUS outputs your current stance.\n";
                return help;
            case 6://Cast
                help = "CAST casts the spell that the user invokes, if their character knows how to use it.\n"
                        + "CAST commands include\n"
                        + "CAST SPELLLIST outputs a list of spells your have both name and spell id.\n"
                        + "CAST SPELL [spell id] cast a spell on your current locked target.\n"
                        + "CAST SPELL [spell id] [target name] cast a spell on target.\n"
                        + "CAST INFO [spell id] outputs details of a spell.\n";
                return help;
            case 7://Lock Target
                help = "LOCK sets the player to aim at the specified target.\n"
                        + "LOCK commands include\n"
                        + "LOCK [target name] sets target to your current locked target.\n"
                        + "LOCK NEXTENEMY set your locked target to the next enemy in the area.\n"
                        + "LOCK NEXTFRIENDLY set your locked target to the next friendly in the area.\n";
                return help;
            case 8://NPC
                help = "NPC [action] lets the player interact with NPCs.\n" + 
                        "NPC TALK activates a dialogue sequence between the player and the NPC.\n" + 
                        "NPC SHOP lets the player buy from the NPC, if the option is available.\n";
                return help;
            //case 9://Player
               // help = "TODO WHEN MULTIPLAYER\n";
               // return help;
            default:
                //Tell the user that the command they entered is invalid.
                help = subCommand + " is not a valid command.\n\n" + helpList();
                return help;
        }
    }
    
    //This method toggles the showing of tooltips in the menu I think I don't actually know how these are supposed to work.
    private String toggleTooltips(){
        String tt;
        if (!tooltips == true){
            tt = "ON\n";
        }else{
            tt = "OFF\n";
        }
        return "TOOLTIPS " + tt;
    }
    
    //Iterate through the map and print all of the commands in the game.
    //Iterate through the map and print all of the commands in the game.
    String listCommands(){
        String list = commands.keySet().toString();
        return list;
    }

    
}
