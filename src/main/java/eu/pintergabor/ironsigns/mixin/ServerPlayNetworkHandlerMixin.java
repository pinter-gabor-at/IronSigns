package eu.pintergabor.ironsigns.mixin;

import java.util.function.Function;
import java.util.stream.Stream;

import eu.pintergabor.ironsigns.config.ModConfigData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.network.ServerGamePacketListenerImpl;


@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerPlayNetworkHandlerMixin {

	@Redirect(method = "handleSignUpdate",
		at = @At(value = "INVOKE",
			target = "Ljava/util/stream/Stream;map(Ljava/util/function/Function;)Ljava/util/stream/Stream;"))
	private Stream<String> nothing(
		Stream<String> instance, Function<String, String> function
	) {
		// function === ChatFormatting::stripFormatting.
		// Strip formatting only if text formatting is disabled in config.
		return ModConfigData.enableSignTextFormatting() ?
			instance :
			instance.map(function);
	}
}
