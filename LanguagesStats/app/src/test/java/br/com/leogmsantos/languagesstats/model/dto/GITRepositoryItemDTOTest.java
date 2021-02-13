package br.com.leogmsantos.languagesstats.model.dto;

import junit.framework.TestCase;

import br.com.leogmsantos.languagesstats.model.base.DTO;

public class GITRepositoryItemDTOTest extends TestCase {

    //Check if the DTO's are respecting the project architecture
    public void testDTOInheritance(){
        GITRepositoryItemDTO itemDto = new GITRepositoryItemDTO();
        GITRepositoryOwnerDTO ownerDTO = new GITRepositoryOwnerDTO();

        //this should respond false because ownderDTO is a Parcelable instance - just an example for a test that wouldn't pass
        assertTrue(itemDto instanceof DTO && ownerDTO instanceof DTO);
    }

}