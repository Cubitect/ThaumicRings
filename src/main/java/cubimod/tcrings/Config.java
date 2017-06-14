package cubimod.tcrings;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config
{
    public static void configurate(File targ)
    {
        Configuration conf = new Configuration(targ);
        try
        {
            conf.load();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conf.save();
        }
    }
}
