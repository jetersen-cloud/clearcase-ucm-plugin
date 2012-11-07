package net.praqma.hudson;

import java.io.File;
import java.util.List;

import net.praqma.clearcase.ucm.entities.Baseline;
import net.praqma.clearcase.ucm.entities.Component;
import net.praqma.clearcase.ucm.entities.Project;
import net.praqma.clearcase.ucm.entities.Stream;
import hudson.model.Action;
import net.praqma.hudson.scm.Polling;
import net.praqma.hudson.scm.Unstable;

public class CCUCMBuildAction implements Action {
	
	private Stream stream;
	private Component component;
    private Project.PromotionLevel promotionLevel;
    private String loadModule;

    /**
     * Create a {@link Baseline} when deliver
     */
    private boolean createBaseline = true;

    /**

     * The naming template for the created {@link Baseline}
     */
    private String nameTemplate;

    private boolean setDescription = false;
    private boolean makeTag = false;
    private boolean recommend = false;
    private boolean forceDeliver = false;

    /**
     * The found {@link Baseline} for the build
     */
	private Baseline baseline;

    private boolean addedByPoller = false;

    /**
     * The list of found {@link Baseline}'s. Typically used when polled
     */
    private List<Baseline> baselines = null;

    /**
     * The type of polling
     */
    private Polling polling;

    /**
     * Determine whether to treat an unstable build as failed or successful
     */
    private Unstable unstable;

    /**
     * Determines whether the post build step should complete the deliver
     */
    private boolean needsToBeCompleted = true;

	/* View, possibly remote */
	private File viewPath;
	private String viewTag;

    /**
     * The created {@link Baseline} when deliver
     */
	private Baseline createdBaseline;
	

	
	public CCUCMBuildAction( Stream stream, Component component ) {
		this.stream = stream;
		this.component = component;
	}

	public Baseline getBaseline() {
		return baseline;
	}

	public void setBaseline( Baseline baseline ) {
		this.baseline = baseline;
	}

	public Baseline getCreatedBaseline() {
		return createdBaseline;
	}

	public void setCreatedBaseline( Baseline createdBaseline ) {
		this.createdBaseline = createdBaseline;
	}
	
	public void setViewPath( File path ) {
		this.viewPath = path;
	}
	
	public File getViewPath() {
		return viewPath;
	}

	public String getViewTag() {
		return viewTag;
	}

	public void setViewTag( String viewTag ) {
		this.viewTag = viewTag;
	}

	public Stream getStream() {
		return stream;
	}

	public Component getComponent() {
		return component;
	}

    public Project.PromotionLevel getPromotionLevel() {
        return promotionLevel;
    }

    public void setPromotionLevel( Project.PromotionLevel promotionLevel ) {
        this.promotionLevel = promotionLevel;
    }

    public String getLoadModule() {
        return loadModule;
    }

    public void setLoadModule( String loadModule ) {
        this.loadModule = loadModule;
    }

    public boolean isCreateBaseline() {
        return createBaseline;
    }

    public void setCreateBaseline( boolean createBaseline ) {
        this.createBaseline = createBaseline;
    }

    public String getNameTemplate() {
        return nameTemplate;
    }

    public void setNameTemplate( String nameTemplate ) {
        this.nameTemplate = nameTemplate;
    }

    public boolean isSetDescription() {
        return setDescription;
    }

    public void setSetDescription( boolean setDescription ) {
        this.setDescription = setDescription;
    }

    public boolean isMakeTag() {
        return makeTag;
    }

    public void setMakeTag( boolean makeTag ) {
        this.makeTag = makeTag;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend( boolean recommend ) {
        this.recommend = recommend;
    }

    public boolean isForceDeliver() {
        return forceDeliver;
    }

    public void setForceDeliver( boolean forceDeliver ) {
        this.forceDeliver = forceDeliver;
    }

    public boolean isAddedByPoller() {
        return addedByPoller;
    }

    public void setAddedByPoller( boolean addedByPoller ) {
        this.addedByPoller = addedByPoller;
    }

    public List<Baseline> getBaselines() {
        return baselines;
    }

    public void setBaselines( List<Baseline> baselines ) {
        this.baselines = baselines;
    }

    public Polling getPolling() {
        return polling;
    }

    public void setPolling( Polling polling ) {
        this.polling = polling;
    }

    public Unstable getUnstable() {
        return unstable;
    }

    public void setUnstable( Unstable unstable ) {
        this.unstable = unstable;
    }

    public boolean isNeedsToBeCompleted() {
        return needsToBeCompleted;
    }

    public void setNeedsToBeCompleted( boolean needsToBeCompleted ) {
        this.needsToBeCompleted = needsToBeCompleted;
    }

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public String getIconFileName() {
		return null;
	}

	@Override
	public String getUrlName() {
		return null;
	}

}
