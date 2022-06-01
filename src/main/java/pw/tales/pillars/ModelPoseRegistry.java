package pw.tales.pillars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import pw.tales.pillars.pose_definition.JsonModel;
import pw.tales.pillars.pose_definition.ModelPose;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ModelPoseRegistry {
    private Gson gson = new GsonBuilder().create();
    private IResourceManager resourceManager;
    private Map<String, ModelPose> poseRegistry = new HashMap<>();

    public ModelPoseRegistry(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public ModelPose getPose(Item item, int meta) {
        return this.poseRegistry.getOrDefault(getID(item, meta), null);
    }

    private String getID(Item item, int meta) {
        return item.getRegistryName().toString() + "/.../" + meta;
    }

    public void registerModel(ResourceLocation location, Item item, int meta) {
        try {
            location = this.getModelLocation(location);
            JsonModel model = this.loadModelPose(location);

            if (model.display == null || model.display.pose == null)
                return;

            poseRegistry.put(getID(item, meta), model.display.pose);
        } catch (FileNotFoundException e) {
            System.err.println(location.toString() + ": not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ResourceLocation getModelLocation(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(),
                "models/item/" + location.getPath() + ".json");
    }

    private JsonModel loadModelPose(ResourceLocation location) throws IOException {
        Reader reader = null;
        IResource resource = null;
        JsonModel modelPose;

        try {
            resource = this.resourceManager.getResource(location);
            reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            modelPose = gson.fromJson(reader, JsonModel.class);
            return modelPose;
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(resource);
        }
    }
}
