package org.example;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

import javax.annotation.Nonnull;
import griffon.inject.MVCMember;


@ArtifactProviderFor(GriffonModel.class)
public class AppModel extends AbstractGriffonModel {

    private AppView view;


    @MVCMember
    public void setView(AppView view) {
        this.view = view;
    }

}