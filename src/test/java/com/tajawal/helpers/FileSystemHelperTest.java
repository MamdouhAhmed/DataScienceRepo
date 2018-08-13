package com.tajawal.helpers;


import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileSystemHelperTest {

    @Test
    public void testGivePathToFile(){
        List<String> allFiles = FileSystemHelper.getAllAvailableFiles(new File("src/test/resources/data/1.json"));
        Assert.assertEquals(allFiles.size(),1);
        Assert.assertEquals(allFiles.get(0),"src/test/resources/data/1.json");
    }

    @Test
    public void testGivePathToDirectory(){
        List<String> allFiles = FileSystemHelper.getAllAvailableFiles(new File("src/test/resources/data"));
        Assert.assertEquals(2,allFiles.size());
        Assert.assertEquals("src/test/resources/data/2.json",allFiles.get(0));
        Assert.assertEquals("src/test/resources/data/3.json",allFiles.get(1));
    }

}
