package com.adrznej.nutcracker.controller.bean;

import javax.ejb.Stateless;

import com.adrznej.nutcracker.controller.remote.NoteControllerRemote;
import com.adrznej.nutcracker.model.NoteModel;

@Stateless
public class NoteControllerBean implements NoteControllerRemote {

	@Override
	public void updateNote(NoteModel note) {
		// TODO Auto-generated method stub
		
	}

}
